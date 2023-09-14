package fr.campusdual.exercicios.exercicio5;


import java.util.List;
import java.util.Scanner;

import static fr.campusdual.exercicios.exercicio5.Menu.foodList;

public class Food {
        private String name;
        private Integer carbos;
        private Integer fats;
        private Integer proteins;

        public Food(String name){
            this.carbos=0;
            this.fats=0;
            this.proteins=0;
            this.name=name;
        }

        public Food(Integer carbos,Integer fats, Integer proteins, String name){
            this.carbos=carbos;
            this.fats=fats;
            this.proteins=proteins;
            this.name=name;

        }

        public Integer getCalories(Integer weight){
            //1 gramo de proteína nos da 4 calorías.
            // 1 gramo de carbohidratos nos da 4 calorías.
            // 1 gramo de grasa nos da 9 calorías
            return(((carbos*4)+(fats*9)+(proteins*4))*weight/100);
        }

        public Integer getCarbos() {
            return carbos;
        }

        public void setCarbos(Integer carbos) {
            this.carbos = carbos;
        }

        public Integer getFats() {
            return fats;
        }

        public void setFats(Integer fats) {
            this.fats = fats;
        }

        public Integer getProteins() {
            return proteins;
        }

        public void setProteins(Integer proteins) {
            this.proteins = proteins;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void showFoodDetails(Food food) {
            System.out.println(food.toString());
        }

    public static void foodCreator(){
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        System.out.println("Bienvenido al creador de alimentos");

        while (!salir) {
            System.out.print("Nombre del alimento (o '0' para salir): ");
            String name = scanner.nextLine();

            if (name.equals("0")) {
                salir=true; // Salir del bucle si el usuario ingresa "salir"
            }
            else {

                System.out.print("Cantidad de carbohidratos (en gramos): ");
                Integer carbos = scanner.nextInt();

                System.out.print("Cantidad de grasas (en gramos): ");
                Integer fats = scanner.nextInt();

                System.out.print("Cantidad de proteínas (en gramos): ");
                Integer proteins = scanner.nextInt();

                // Consumir la línea en blanco después del nextInt()
                scanner.nextLine();

                Food food = new Food(carbos, fats, proteins,name);

                foodList.add(food); // Agregar el alimento al ArrayList

                System.out.println("Alimento creado y almacenado en la lista.");
            }

            // Mostrar resumen de todos los alimentos almacenados en el ArrayList
            for (Food food : foodList) {
                System.out.println("Resumen del alimento:");
                System.out.println("Nombre: " + food.getName());
                System.out.println("Carbohidratos: " + food.getCarbos() + " gramos");
                System.out.println("Grasas: " + food.getFats() + " gramos");
                System.out.println("Proteínas: " + food.getProteins() + " gramos");
                System.out.println("Calorías (para 100 gramos): " + food.getCalories(100) + " calorías");
                System.out.println("--------------------------");
            }
        }

    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cantidad de nutrientes por 100 gramos :\n");

                    sb.append("Nombre: ").append(this.getName()).append("\n");
                    sb.append("Carbohidratos: ").append(this.getCarbos()).append(" gramos\n");
                    sb.append("Grasas: ").append(this.getFats()).append(" gramos\n");
                    sb.append("Proteínas: ").append(this.getProteins()).append(" gramos\n");
                    sb.append("Calorías (para 100 gramos): ").append(this.getCalories(100)).append(" calorías\n");
                    sb.append("--------------------------\n").toString();

        return sb.toString();
    }

    public void showFoodList() {
            for(Food food:foodList){
                food.showFoodDetails(food);
            }
    }
}

