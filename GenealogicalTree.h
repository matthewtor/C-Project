#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

//to keep track of the two "parents"
enum Direction {LEFT = 0, RIGHT = 1};

typedef struct node {
    char name[20];
    unsigned int age;
    struct node* parents[2];
} node;

bool isNull(node* n) {return n == NULL;}

//A node is a leaf if is not null and if it doesn't have any "parents"
bool isLeaf(node* n) {
    return !isNull(n) && isNull(n->parents[RIGHT]) && isNull(n->parents[LEFT]);
}

node* newNode(char name[], unsigned int age) {
    node* tmp = (node *) malloc(sizeof(struct node));
    strcpy(tmp->name, name);
    tmp->age = age;
    
    tmp->parents[LEFT] = tmp->parents[RIGHT] = NULL;
    return tmp;
}


//This function simply prints node's informations only if the node is not null
void printNode(node* n) {
    if(isNull(n)) return;
    printf("Name: %s - Age: %d\n", n->name, n->age);
    printNode(n->parents[LEFT]);
    printNode(n->parents[RIGHT]);
}

/* ====================================================== */

typedef struct tree {
    node* root;
} tree;

tree newTree(node* root) {
    tree t;
    t.root = root;
    return t;
}

void freeTree(tree t) {
    free(t.root);
}

//This function prints the tree horizontally
void print2D(node* n, int space) {
    if(isNull(n)) return;
    space += 5;
    print2D(n->parents[RIGHT], space);
    printf("\n");
    for(int i = 5; i < space; i++)
        printf(" ");
    printf("Name: %s - Age: %d\n", n->name, n->age);
    print2D(n->parents[LEFT], space);
}

//This function adds a "parent" to his "son", only if the son is not null and if it doesn't already have a parent in the given direction
bool addParent(node* son, node* parent, enum Direction d) {
    if(isNull(son) || !isNull(son->parents[d])) return false;

    if(!isNull(parent)){
        son->parents[d] = parent;
        return true;
    }
}

//This function adds two "parents" to their "son" and puts the youngest "parent" on the left
bool addParents(node* son, node* parent_one, node* parent_two) {
    if(parent_one->age < parent_two->age)
        return addParent(son,parent_one,LEFT) && addParent(son,parent_two,RIGHT);

    return addParent(son,parent_two,LEFT) && addParent(son,parent_one,RIGHT);
}

//This function adds a "parent" node to his "son" and checks where it can add the new "parent"
void add(node* son, node* new_node) {
    if(isNull(son) || isNull(new_node)) return;

    bool left = addParent(son, new_node, LEFT);
    if(!left) {
        bool right = addParent(son, new_node, RIGHT);
        if(!right) {
            printf("Cannot add a new parent\n");
            return;
        }
        else {
            printf("Right parent added\n");
            return;
        }
    }
    else {
        printf("Left parent added\n");
        return;
    }
}

//This function searches a node by the given name
node* searchNode(node* root, char name[]) {
    node* tmp = NULL;
    if(isNull(root)) return NULL;

    if(strcmp(root->name,name) == 0) return root;
    if(!isNull(root->parents[LEFT])) {
        tmp = searchNode(root->parents[LEFT],name);
        if(!isNull(tmp)) return tmp;
    }
    if(!isNull(root->parents[RIGHT])) {
        tmp = searchNode(root->parents[RIGHT],name);
        if(!isNull(tmp)) return tmp;
    }
    return tmp;
}
//This function searches a node by the given age
node* searchNodeByAge(node* root, unsigned int age) {
    node* tmp = NULL;
    if(isNull(root)) return NULL;

    if(root->age == age) return root;
    if(!isNull(root->parents[LEFT])) {
        tmp = searchNodeByAge(root->parents[LEFT],age);
        if(!isNull(tmp)) return tmp;
    }
    if(!isNull(root->parents[RIGHT])) {
        tmp = searchNodeByAge(root->parents[RIGHT],age);
        if(!isNull(tmp)) return tmp;
    }
    return tmp;
}

/*
======================================================
I had some issues while trying to remove a node from
the tree, so I'll leave here what I've done.
======================================================

void removeNode(node* remove) {
   if(isNull(remove)) return;

   removeNode(remove->parents[LEFT]);
   removeNode(remove->parents[RIGHT]);
   free(remove);
   remove = NULL;
}*/

//This function prints the node only if it's a leaf node
void printLeaves(node* n) {
    if(isNull(n)) return;

    printLeaves(n->parents[LEFT]);
    if(isLeaf(n)) printNode(n);
    printLeaves(n->parents[RIGHT]);
}

//This function finds the max age in the tree and returns it
int findMax(node* n) {
    if(isNull(n)) return 0;

    int left_max, right_max;
    int max = n->age;
    if(!isNull(n->parents[LEFT])) {
        left_max = findMax(n->parents[LEFT]);
        max = (max > left_max) ? max : left_max;
    }
    if(!isNull(n->parents[RIGHT])) {
        right_max = findMax(n->parents[RIGHT]);
        max = (max > right_max) ? max : right_max;
    }
    return max;
}