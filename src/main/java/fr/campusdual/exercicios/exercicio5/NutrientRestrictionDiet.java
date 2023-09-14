package fr.campusdual.exercicios.exercicio5;

import java.util.*;

import static fr.campusdual.exercicios.exercicio5.Day.selectDay;
import static fr.campusdual.exercicios.exercicio5.Menu.*;

public class NutrientRestrictionDiet extends Diet{
    private Integer maxFats;
    private Integer maxCarbs;
    private Integer maxProtein;

    public NutrientRestrictionDiet(Map<Day, Map<Food, Integer>> dietMap, Integer maxFats, Integer maxCarbs, Integer maxProtein) {
        super(dietMap);
        this.maxFats = maxFats;
        this.maxCarbs = maxCarbs;
        this.maxProtein = maxProtein;
    }

    public NutrientRestrictionDiet(String name, Map<Day, Map<Food, Integer>> dietMap, Integer maxFats, Integer maxCarbs, Integer maxProtein) {
        super(name, dietMap);
        this.maxFats = maxFats;
        this.maxCarbs = maxCarbs;
        this.maxProtein = maxProtein;
    }

    public NutrientRestrictionDiet(String name, Map<Day, Map<Food, Integer>> dietMap) {
        super(name, dietMap);
    }
    public NutrientRestrictionDiet() {
    }
    public static NutrientRestrictionDiet nutrientRestrictionSelectFood() {


        if (foodList.isEmpty()){
            System.out.println("Todavía no hay alimentos en la lista, cree alguno : ");
            Food.foodCreator();
        }


        Day selectedDay = selectDay();


        NutrientRestrictionDiet nutrientRestrictionDietFromSelectFood= new NutrientRestrictionDiet();
        Map<Food, Integer> foodMap = new HashMap<>();

        boolean salir= false;
        while (!salir){

            System.out.println("0. Salir");

            for (int i = 0; i < foodList.size(); i++) {

                Food food = foodList.get(i);
                System.out.println("Resumen del alimento #" + (i + 1) + ":");
                System.out.println("Nombre: " + food.getName());
                System.out.println("Carbohidratos: " + food.getCarbos() + " gramos");
                System.out.println("Grasas: " + food.getFats() + " gramos");
                System.out.println("Proteínas: " + food.getProteins() + " gramos");
                System.out.println("Calorías (para 100 gramos): " + food.getCalories(100) + " calorías");
                System.out.println("--------------------------");

            }

            System.out.print("Elige un alimento (ingresa el número correspondiente), o '0' para salir : ");
            int selectedFoodIndex = Kb.nextInt();

            if (selectedFoodIndex == 0) {
                System.out.println("Saliendo del programa.");
                salir=true;

            } else if(selectedFoodIndex >= 1 && selectedFoodIndex <= foodList.size()) {
                Food selectedFood = foodList.get(selectedFoodIndex - 1);

                System.out.print("Ingresa la cantidad en gramos de " + selectedFood.getName() + ": ");
                Integer grams = Kb.nextInt();

                // Agregar el alimento seleccionado como clave y la cantidad en gramos como valor al dietMap
                foodMap.put(selectedFood, grams);
                salir=nutrientRestrictionDietFromSelectFood.overMaxParameter(foodMap);
                nutrientRestrictionDietFromSelectFood.addFood(selectedFood,grams,selectedDay);


                System.out.println(ALIMENTO_ENGADIDO);
            } else {
                System.out.println(INVALID_SELECTION);
            }
        }
        return nutrientRestrictionDietFromSelectFood;
    }

    public static NutrientRestrictionDiet selectNutrientDiet() {
        if (dietList.isEmpty()){
            return nutrientRestrictionSelectFood();
        }
        List<NutrientRestrictionDiet> nutrientRestrictionDietList= new ArrayList<>();
        System.out.println("Seleccione una de las siguientes dietas para añadirla a su lista : ");
        for (int i=0;i<dietList.size();i++) {

            if (dietList.get(i) instanceof NutrientRestrictionDiet) {

                nutrientRestrictionDietList.add((NutrientRestrictionDiet) dietList.get(i));
            }
        }
        for (int i= 0; i<nutrientRestrictionDietList.size();i++){

            System.out.println(i+1+". "+nutrientRestrictionDietList.get(i).toString());
        }

        Integer dietOption = Kb.nextInt();

        return nutrientRestrictionDietList.get(dietOption-1);
    }

    public boolean overMaxParameter(Map<Food, Integer> foodMap) {
        Set<Food> claves = foodMap.keySet();
        boolean overMaxParameter=false;
        Integer totalCarbs=0;
        Integer totalFat=0;
        Integer totalProtein=0;
        for (Food clave: claves){
            totalFat += (clave.getFats()*foodMap.get(clave));
            totalCarbs += (clave.getCarbos()*foodMap.get(clave));
            totalProtein += (clave.getProteins()*foodMap.get(clave));

            if (totalFat>this.maxFats){
                System.out.println("The actual fat of this diet is over the maxFat," +
                        " here is the guilty : "+ clave.getName());
                overMaxParameter=true;
            }
            if (totalProtein>maxProtein){
                System.out.println("The actual protein of this diet is over the maxProtein," +
                        " here is the guilty : "+ clave.getName());
                overMaxParameter=true;
            }
            if (totalCarbs>maxCarbs){
                System.out.println("The actual carbs of this diet is over the maxCarbs," +
                        " here is the guilty : "+ clave.getName());
                overMaxParameter=true;
            }
        }
        return overMaxParameter;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Alimentos en la Dieta:\n");

        Day[] days = Day.values();

        for (Day day : days) {
            System.out.println("Día " + day.getName());
            for (Food food : this.getFoodMap(day).keySet()) {
                sb.append("Nombre: ").append(food.getName()).append("\n");
                sb.append("Carbohidratos: ").append(food.getCarbos()).append(" gramos\n");
                sb.append("Grasas: ").append(food.getFats()).append(" gramos\n");
                sb.append("Proteínas: ").append(food.getProteins()).append(" gramos\n");
                sb.append("Calorías (para 100 gramos): ").append(food.getCalories(100)).append(" calorías\n");
                sb.append("--------------------------\n");
            }
        }

        if(this.maxCarbs!=null) {

            sb.append("Esta Dieta está pensada para un máximo de : "
                    + this.maxCarbs + " carbohidratos . ");
        }

        if (this.maxFats!=null){
            sb.append("Esta Dieta está pensada para un máximo de : "
                    +this.maxFats+" grasas . ");
        }
        if (this.maxProtein!=null){
            sb.append("Esta Dieta está pensada para un máximo de : "
                    +this.maxProtein+" proteínas . ");
        }
        // Recorrer los días de la semana usando un bucle for
        for (Day day : days) {
            if (!this.getFoodMap(day).isEmpty()){
                sb.append(" Calorías totales de la Dieta : ").append(calculateTotalCalories(this.getFoodMap(day)).toString());
        }
        }
        return sb.toString();
    }

    public Integer getMaxFats() {
        return maxFats;
    }

    public  void setMaxFats(Integer maxFats) {
        this.maxFats = maxFats;
    }

    public Integer getMaxCarbs() {
        return maxCarbs;
    }

    public void setMaxCarbs(Integer maxCarbs) {
        this.maxCarbs = maxCarbs;
    }

    public Integer getMaxProtein() {
        return maxProtein;
    }

    public void setMaxProtein(Integer maxProtein) {
        this.maxProtein = maxProtein;
    }
    static void modificarRestriccionNutrientes(NutrientRestrictionDiet nutrientRestrictionDiet) {
        if (nutrientRestrictionDiet==null){
            System.out.println("No has seleccionado ninguna dieta. ");
        }
        System.out.println("Introduzca el valor de la restricción de carbohidratos :");
        Integer maxCarbo = Kb.nextInt();
        System.out.println("Límite de carbohidratos elegido : "+ maxCarbo);

        nutrientRestrictionDiet.setMaxCarbs(maxCarbo);

        System.out.println("Introduzca el valor de la restricción de grasas :");
        Integer maxFat = Kb.nextInt();
        System.out.println("Límite de grasas elegido : "+ maxFat);
        nutrientRestrictionDiet.setMaxFats(maxFat);

        System.out.println("Introduzca el valor de la restricción de proteínas :");
        Integer maxProtein = Kb.nextInt();
        System.out.println("Límite de proteínas elegido : "+ maxProtein);
        nutrientRestrictionDiet.setMaxProtein(maxProtein);

        //Reemplazamos dieta
        for (Diet diet:actualUser.getDietList()){
            if (diet.getName().equals(nutrientRestrictionDiet.getName())){
               actualUser.removeDietFromList(diet);

            }
        }
        actualUser.addDietToList(nutrientRestrictionDiet);
        dietList.add(nutrientRestrictionDiet);
        actualParameterRestrictionDiet=nutrientRestrictionDiet;
    }

}
