package fr.campusdual.exercicios.exercicio5;

import java.util.HashMap;
import java.util.Map;

public class CaloriesRestrictionDiet extends Diet{

    private Integer maxCalories;
    public CaloriesRestrictionDiet() {
        super();

    }

    public CaloriesRestrictionDiet(String name, Map<Day, Map<Food, Integer>> dietMap, Integer maxCalories) {
        super(name, dietMap);
        this.maxCalories = maxCalories;
    }


    public CaloriesRestrictionDiet(Map<Day, Map<Food, Integer>> dietMap, Integer maxCalories) {
        super(dietMap);
        this.maxCalories = maxCalories;
    }

    public Integer getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(Integer maxCalories) {
        this.maxCalories = maxCalories;
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
            if (!this.getFoodMap(day).isEmpty()){
                for (Food food : this.getFoodMap(day).keySet()) {
                    sb.append("Nombre: ").append(food.getName()).append("\n");
                    sb.append("Carbohidratos: ").append(food.getCarbos()).append(" gramos\n");
                    sb.append("Grasas: ").append(food.getFats()).append(" gramos\n");
                    sb.append("Proteínas: ").append(food.getProteins()).append(" gramos\n");
                    sb.append("Calorías (para 100 gramos): ").append(food.getCalories(100)).append(" calorías\n");
                    sb.append("--------------------------\n");
                    sb.append(" Calorías totales del día : ").append(calculateTotalCalories(this.getFoodMap(day)).toString());
                }
            }
        }

        if (this.maxCalories!=null) {
            sb.append("Calorías máximas : " + this.maxCalories);
        }

        return sb.toString();
    }
}
