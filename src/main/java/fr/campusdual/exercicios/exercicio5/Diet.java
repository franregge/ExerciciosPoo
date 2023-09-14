package fr.campusdual.exercicios.exercicio5;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static fr.campusdual.exercicios.exercicio5.Day.selectDay;
import static fr.campusdual.exercicios.exercicio5.Menu.*;
import static fr.campusdual.exercicios.exercicio5.TMBRestrictionDiet.TMBRestrictionDietMenu;
import static fr.campusdual.exercicios.exercicio5.User.modificarParametrosTMBActuales;

/*
* Escribe una clase dieta, que teniendo en cuenta una serie de alimentos,
*  vaya sumando cantidades en gramos y calcule cuentas calorías, carbohidratos, proteinas
* y grasas genera la ingesta
La clase dieta tiene que tener las siguientes funcionalidades:
	-Diet(): crea una dieta sin límite de calorías
	-Diet(Integer maxCalories): crea una dieta con un máximo de calorías, en cuanto se supera ese máximo cuando se adjunta un alimento se despliega un error
	-Diet(Integer maxFats, Integer maxCarbs, Integer maxProtein): crea una dieta con un máximo de estos tres valores, si se supera alguno de ellos cuando se adjunta un alimento se indicara cual y desplegará un error
	-Diet(Boolean women, Integer age, Integer height, Integer weight): Calcula el metabolismo basal de la persona y lo asigna como máximo de calorías en la dieta
		--CALCULAR METABOLISMO BASAL
			E = edad
			A = altura en centímetros
			P = peso en kilos

			Fórmula Hombres: TMB = 10P + 6,25A – 5E + 5
			Fórmula Mujeres: TMB = 10P + 6,25A – 5E – 161
	-addFood(Food,Integer): agrega un alimento y una cantidad en gramos
	-getTotalCalories(): devuelve el total de calorías
	-getTotalCarbs(): devuelve el total de carbohidratos
	-getTotalFats(): devuelve el total de grasas
	-getTotalProtein(): devuelve el total de proteinas
*
* */
public class Diet {
    private String name;
    private Map<Day,Map<Food,Integer>> dietMap;
    public Diet(String name,Map<Day, Map<Food, Integer>> dietMap) {
        this.dietMap = dietMap;
        this.name=name;
    }
    public Diet(Map<Day, Map<Food, Integer>> dietMap) {
        this.dietMap = dietMap;
    }
    public Diet() {
        this.dietMap = new HashMap<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Map<Day, Map<Food, Integer>> getDietMap() {
        return dietMap;
    }
    public Map<Food, Integer> getFoodMap(Day day) {

        return this.dietMap.get(day);
    }
    public void setFoodMap(Map<Food,Integer> foodMap,Day day) {

        this.dietMap.put(day, foodMap);
    }
    public void setDietMap(Map<Day, Map<Food, Integer>> dietMap) {
        this.dietMap = dietMap;
    }

    //crea una dieta con un máximo de calorías,
    // en cuanto se supera ese máximo cuando se adjunta un alimento se despliega un error
    //crea una dieta con un máximo de estos tres valores,
    // si se supera alguno de ellos cuando se adjunta un alimento se indicara qué parámetro
    // se pasa y desplegará un error
    //Calcula el metabolismo basal de la persona y lo asigna como máximo de calorías
    // en la dieta
    //CALCULAR METABOLISMO BASAL
           // E = edad
    //A = altura en centímetros
          //  P = peso en kilos
    //Fórmula Hombres: TMB = 10P + 6,25A – 5E + 5
    //Fórmula Mujeres: TMB = 10P + 6,25A – 5E – 161

    public static void dietCreator()  {
        boolean salir = false;
        while (!salir) {

            System.out.println("Seleccione una opción de dieta: ");
            System.out.println("1. Dieta sin límite de calorías.");
            System.out.println("2. Dieta con límite de calorías.");
            System.out.println("3. Dieta con límite de carbos, grasas y proteínas.");
            System.out.println("4. Dieta con límite de calorías según tu TMB.");
            System.out.println(SALIR);

            Integer option = Kb.nextInt();

            switch (option){
                case 1:
                    noRestrictionDietMenu();

                case 2:
                    modificarRestriccionCalorica();
                    caloriesRestrictionDiet();


                case 3:
                    nutrientRestrictionDiet();


                case 4:
                    modificarParametrosTMBActuales();
                    TMBRestrictionDietMenu();


                case 5:
                    salir= true;
                    break;
                default:
                    break;


            }

        }
    }
    public boolean overMaxCalories(Map<Food,Integer>foodMap, Integer maxCalories) {
        boolean overMaxCalories=false;

        Integer actualDietCalories =calculateTotalCalories(foodMap);
        if (actualDietCalories>maxCalories){
            System.out.println("Tu dieta no puede pasarse de "+maxCalories+". Añadiendo este alimento serían : "+actualDietCalories);
            overMaxCalories= true;

        }return overMaxCalories;
    }
    public Integer calculateTotalCalories (Map<Food,Integer> foodMap){
        Integer totalCalories = 0;
        Set<Food> claves=foodMap.keySet();
        for (Food clave : claves){
            totalCalories +=clave.getCalories(foodMap.get(clave));
        }
        return totalCalories;
    }
    public Integer calculateTotalWeight (Map<Food,Integer> foodMap){
        Integer totalDietWeight =0;
        Set<Food> claves=foodMap.keySet();
       for (Food clave : claves){
           totalDietWeight+=foodMap.get(clave);
       }
       return totalDietWeight;
    }
    public static Diet selectFood(){

        if (foodList.isEmpty()){
            System.out.println("Todavía no hay alimentos en la lista, cree alguno : ");
            Food.foodCreator();
        }

        Map<Food, Integer> dietFoodMap = new HashMap<>();
        Diet diet= new Diet();

        if (diet.getName()==null){

            System.out.println("Escribe un nombre para tu dieta sin restricciones");

            String dietName = Kb.nextLine();

            diet.setName(dietName);

        }


        Day selectedDay = selectDay();

        System.out.println("0. Salir");// Map para la dieta

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

        System.out.print("Elige un alimento (ingresa el número correspondiente),o '0' para salir : ");
        int selectedFoodIndex = Kb.nextInt();

        if (selectedFoodIndex == 0) {
            System.out.println("Saliendo del programa.");
        } else if(selectedFoodIndex >= 1 && selectedFoodIndex <= foodList.size()) {
            Food selectedFood = foodList.get(selectedFoodIndex - 1);

            System.out.print("Ingresa la cantidad en gramos de " + selectedFood.getName() + ": ");
            Integer grams = Kb.nextInt();

            // Agregar el alimento seleccionado como clave y la cantidad en gramos como valor al dietMap
            dietFoodMap.put(selectedFood, grams);
            diet.setFoodMap(dietFoodMap,selectedDay);


            System.out.println(ALIMENTO_ENGADIDO);

        } else {
            System.out.println(INVALID_SELECTION);
        }
        dietList.add(diet);

        return diet;
    }
    public void addFood(Food food, Integer weight,Day day) {

        if (getFoodMap(day).containsKey(food)){
            System.out.println("You cant repeat a food in a diet");
        }else {
            Map<Food,Integer>newFoodMap=getFoodMap(day);

            newFoodMap.put(food,weight);

            setFoodMap(newFoodMap,day);
        }
    }
    public static void showDietDetails(Diet diet){
        System.out.println(diet.toString());
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
                }
            }
        }
        return sb.toString();
    }


}
