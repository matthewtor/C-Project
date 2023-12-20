#include "GenealogicalTree.h"
#include <unistd.h>

/*
==========================================================
I haven't figured out how to create nodes
from file. I can read data from file but
I haven't found any solution to create nodes
and connect them.
Here is the code to print all words separated by spaces.
==========================================================

#define MAX_LINES 6
#define MAX_LEN 30

void splitString(char buffer[]) {
    int init_size = strlen(buffer);
	char *ptr = strtok(buffer, " \n\t");

	while(ptr != NULL) {
		printf("Token: %s\n", ptr);
		ptr = strtok(NULL, " \n\t");
	}
    printf("\n");
}

void readFromFile() {
    char data[MAX_LINES][MAX_LEN];

    FILE* file;
    file = fopen("./familyinfo.txt", "r");
    if(!file) return 1;

    int curline = 0;
    while(!feof(file) && !ferror(file))
        if(fgets(data[curline], MAX_LEN, file) != NULL)
            curline++;

    fclose(file);

    
    for(int i = 0; i < curline; i++)
        splitString(data[i]);
}
*/

tree createTree() {
    node* jon = newNode("Jon", 10);
    node* alex = newNode("Alex", 39);
    node* sam = newNode("Sam", 40);

    node* kris = newNode("Kris", 65);
    node* robin = newNode("Robin", 68);
    
    node* mary = newNode("Mary", 63);

    addParents(jon,alex,sam);
    addParents(alex,kris,robin);
    addParent(sam,mary,LEFT);

    tree t = newTree(jon);
    return t;
}

void printMenu() {
    printf("=======================================\n");
    printf("Option 1: Add a family member\n");
    printf("Option 2: Print the tree\n");
    printf("Option 3: Find the oldest person\n");
    printf("Option 4: Remove a person\n");
    printf("Option 5: Print the tree leaves\n");
    printf("Option 6: Exit the application\n");
    printf("=======================================\n");
}

void option_one(tree myTree) {
    char name[20];
    char son_name[20];
    unsigned int age = 0;
    printf("Insert the name and press ENTER: ");
    scanf("%s", name);
    printf("Insert the age and press ENTER: ");
    scanf("%d", &age);
    printf("Who's the child of this new member? ");
    scanf("%s", son_name);

    node* new_member = newNode(name,age);
    node* son = searchNode(myTree.root,son_name);
    add(son, new_member);
}

void option_two(tree myTree) {
    //printf("\n");
    print2D(myTree.root,0);
    printf("\n");
}

void option_three(tree myTree) {
    int max = findMax(myTree.root);
    node* oldest;
    oldest = searchNodeByAge(myTree.root, max);
    if(!isNull(oldest)) {
        printf("The oldest person is: ");
        printNode(oldest);
    }
    else printf("Error while searching the oldest\n");
}

/*void option_four(tree myTree) {
    char name[20];
    printf("Insert the name and press ENTER: ");
    scanf("%s", name);

    node* remove;
    remove = searchNode(myTree.root, name);
    removeNode(remove);
    //remove = NULL;
}*/

void option_five(tree myTree) {
    printf("Leaves are:\n");
    printLeaves(myTree.root);
}

void cleanMenu(int seconds) {
    sleep(seconds);
    system("clear");
}

int main(void) {
    tree myTree = createTree();
    int option;
    while(option != 6) {
        printMenu();
        printf("Insert a valid option and press ENTER: ");
        scanf("%d",&option);
        switch(option) {
            case 1: ;
                option_one(myTree);
                cleanMenu(3);
                break;
            case 2:
                option_two(myTree);
                cleanMenu(5);
                break;
            case 3:
                option_three(myTree);
                cleanMenu(3);
                break;
            case 4:
                //option_four(myTree);
                printf("Option 4 is not correctly implemented\n");
                //cleanMenu(2);
                break;
            case 5:
                option_five(myTree);
                cleanMenu(3);
                break;
            case 6:
                printf("Bye bye!\n");
                cleanMenu(1);
                break;
            default:
                printf("\nOption is not valid!\n\n");
                cleanMenu(1);
                break;
        }
    }

    freeTree(myTree);
    myTree.root = NULL;
    return 0;
}