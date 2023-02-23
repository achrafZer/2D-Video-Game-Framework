
#2D Video Game Framework

##Presentation of the framework:

Welcome to the 2D Video Game Framework in Java!

This software is a 2D game development framework that follows a Model-View-Controller (MVC) architecture to facilitate separation of concerns and code maintenance.

The framework is organized into three main directories:

###The controller directory:
The controller directory contains the AI engine through the abstract class AIEngine, as well as the GameApp class which is the core of the program. Developers must extend this class to use the framework and add their own game logic.

###The model directory:
The model directory contains the physical engine, with primarily the PhysicalEngine class. This class manages the simulation of physical objects in the game such as collisions and movements.

###Te view directory:
The view directory contains the graphical engine, which manages the display of objects and backgrounds in the game. Developers can customize this part of the framework to create their game's user interface.

The framework is designed to be easily extendable and customizable. Developers can add new functionality by extending existing classes or creating new classes.

To get started with the framework, you can create a new class that extends GameApp and implement your own game logic in the provided methods. You can also use the AI engine to create enemies or non-player characters that react to the player's actions.

For more information on using the framework, see the documentation provided in the docs directory. Here, you will find code examples and detailed explanations of each part of the framework.

We hope this framework will be helpful for creating your 2D games. Please feel free to provide feedback and suggestions to improve this software.

## User Quickstart

If you want to use my framework, these are the steps you should follow:
1. Extending GameApp in your main class:
```java
import controller.GameApp;

public class MyGame extends GameApp{
    public UserTest(int width, int length) throws Exception {
                super(width, length);
    }

    public void setManagers(){}
    public void initEntities(){}
}
```
As you can see, your class must contain some code for the following methods: setManagers();initEntities();

setManager should help you to create your own managers for collisions, inputs and AI. you should create them, and make 
them as extensions to my own classes.

initEntitie is the method where you will create your initial entities. For every entity you create, you need to define 
its for, position, movement and you need to add it to the game, using addEntity()


```java
public void initEntities() {
    EShape shape = new Rectangle(0, 0, 100, 30);
    EntityParameters snakeParameters = new EntityParameters(shape);
    Mouvement snakeMvt = new ProjectileMouvement(Direction.RIGHT);
    Entity snake = new Entity(snakeParameters, snakeMvt);
    this.addEntity(snake);
    this.setActiveEntity(snake);

    EShape shape1 = new Circle(150, 150, 30, 30);
    EntityParameters appleParameters = new EntityParameters(shape1);
    Mouvement appleMvt = new ProjectileMouvement(Direction.UP);
    appleMvt.setActive(false);
    Entity apple = new Entity(appleParameters, appleMvt);
    this.addEntity(apple);
    }
```

To display the game, you have to call the launch method

```java
    public static void main(String[] args) throws Exception {
        MyGame mygame = new MyGame(500, 500);
        mygame.launch();
    }
```