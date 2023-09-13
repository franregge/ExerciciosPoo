package fr.campusdual.exercicios.exercicio5;

import java.util.Scanner;

import static fr.campusdual.exercicios.exercicio4.Menu.users;

public class Repositorio {
    User user;
    Food food;
    public Repositorio() {
    }


    public void addUser(User user){
        users.add(user);
    }
    public void userMenu(){

        Scanner scanner = new Scanner(System.in);
        boolean salir=false;
        while (!salir) {

            System.out.println("Welcome to your Diet creator");


            System.out.println("1. Login");
            System.out.println("2. Crear cuenta de usuario");
            System.out.println("3. Dar de baja Usuario");
            System.out.println("4. Detalles de usuarios");
            System.out.println("5. Lista de alimentos");
            System.out.println("6. Salir");
            Integer input = scanner.nextInt();
            switch (input) {
                case 1:
                    user.login();
                case 2:

                    user.userCreator();

                case 3:

                    user.userDeleter();

                case 4:

                    user.showUsersDetails();

                case 5:

                    food.showFoodDetails();

                case 7:
                    salir=true;
                default:
                    break;

            }
        }

    }

}
