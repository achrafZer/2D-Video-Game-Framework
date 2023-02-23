
# Projet: Framework pour le developpement de jeux vidéos 2D

Le but du projet est de créer un framework qui facilite le developpement de jeux vidéos en 2D.

Aucune installation n'est nécessaire, il suffit d'importer le projet comme librairie pour accéder aux fonctionnalités du framework.

## User Quickstart

En tant que developpeur de jeux vidéos 2D qui souhaite utiliser notre framework, voici les étapes à suivre:

1. Création de la classe MyGame: votre classe où vous allez commencer à construire votre jeu. Il faut bien penser à indiquer les dimension du plateau de jeu! Pour exploiter les fonctionnalités du framework, cette classe devra étendre le moteur kernel GameApp.
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
Comme on peut observer, votre classe elle devra implémenter les méthodes suivantes:
```java
public abstract void setManagers();
public abstract void initEntities();
```

La méthode setManagers vous permettra de personnaliser la façon dont vous souhaitez gérer vos collisions entre entités ainsi que vos entrées clavier et/ou souris.
Pour cela, vous devrez créer des gestionnaires. Si vous ne savez pas encore comment créer vos gestionnaires, vous pouvez simplement l'implémenter comme suit:
```java
public void setManagers() {
        setInputsManager(new UserTestInputsManager());
        setAiEngine(new UserTestAIEngine());
    }
```

La méthode initEntities est l'endroit où vous allez créer les entités de base de votre jeu. Il faudra, pour chaque entité, définir leur forme, position, mouvement, et les rajouter au plateau de jeu. Voici comment créer deux entités de base, un rectangle et un cercle:

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

Biensur, d'autres méthodes peuvent être rajoutés dans cette classe pour faciliter la tâche de création des entités, ou même pour la création des gestionnaires. Cela dépend de l'utilisateur.

Finalement, pour lancer le jeu, on instance notre classe dans la méthode main et on fait appel à la méthode launch(). Cette méthode affichera le plateau de jeu avec les entités qu'on a rajouté et les mettra en mouvement:
```java
    public static void main(String[] args) throws Exception {
        MyGame mygame = new MyGame(500, 500);
        mygame.launch();
    }
```