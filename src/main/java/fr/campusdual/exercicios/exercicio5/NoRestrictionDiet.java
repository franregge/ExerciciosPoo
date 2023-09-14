package fr.campusdual.exercicios.exercicio5;

import java.util.HashMap;
import java.util.Map;

import static fr.campusdual.exercicios.exercicio5.Day.selectDay;
import static fr.campusdual.exercicios.exercicio5.Food.foodCreator;
import static fr.campusdual.exercicios.exercicio5.Food.printFoodElectionMenu;
import static fr.campusdual.exercicios.exercicio5.Menu.*;

public class NoRestrictionDiet extends Diet{


    public NoRestrictionDiet(String name, Map<Day, Map<Food, Integer>> dietMap) {
        super(name, dietMap);
    }

    public NoRestrictionDiet(Map<Day, Map<Food, Integer>> dietMap) {
        super(dietMap);
    }

    public NoRestrictionDiet() {
        super();
    }

    public static NoRestrictionDiet selectFood(){

        boolean salir = false;

        if (foodList.isEmpty()){
            System.out.println("Todavía no hay alimentos en la lista, cree alguno : ");
            foodCreator();
        }

        System.out.println("Introduzca el nombre de su nueva dieta");
        String dietName = Kb.nextLine();


        Map<Food, Integer> dietFoodMap = new HashMap<>();
        NoRestrictionDiet noRestrictionDietFromSelectFood = new NoRestrictionDiet();

        Day selectedDay = selectDay();

        while (!salir) {


            printFoodElectionMenu();


            System.out.print("Elige un alimento (ingresa el número correspondiente),o '0' para salir : ");
            int selectedFoodIndex = Kb.nextInt();


            if (selectedFoodIndex == 0) {
                System.out.println("Saliendo del programa.");
                salir=true;
            } else if (selectedFoodIndex >= 1 && selectedFoodIndex <= foodList.size()) {
                Food selectedFood = foodList.get(selectedFoodIndex - 1);

                System.out.print("Ingresa la cantidad en gramos de " + selectedFood.getName() + ": ");
                Integer grams = Kb.nextInt();

                // Agregar el alimento seleccionado como clave y la cantidad en gramos como valor al dietMap
                dietFoodMap.put(selectedFood, grams);
                noRestrictionDietFromSelectFood.setFoodMap(dietFoodMap, selectedDay);

                System.out.println(ALIMENTO_ENGADIDO);

            } else {
                System.out.println(INVALID_SELECTION);
            }
        }

        return noRestrictionDietFromSelectFood;

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Alimentos en la Dieta:\n");
        // Obtener todos los valores del enum Day
        Day[] days = Day.values();

        // Recorrer los días de la semana usando un bucle for
        for (Day day : days) {
            System.out.println("Día "+day.getName());
            if (this.getFoodMap(day)!= null && !this.getFoodMap(day).isEmpty()) {
                for (Food food : this.getFoodMap(day).keySet()) {
                    sb.append("Nombre: ").append(food.getName()).append("\n");
                    sb.append("Carbohidratos: ").append(food.getCarbos()).append(" gramos\n");
                    sb.append("Grasas: ").append(food.getFats()).append(" gramos\n");
                    sb.append("Proteínas: ").append(food.getProteins()).append(" gramos\n");
                    sb.append("Calorías (para 100 gramos): ").append(food.getCalories(100)).append(" calorías\n");
                    sb.append("--------------------------\n");
                }
            }
        }
        return sb.toString();
    }

}
