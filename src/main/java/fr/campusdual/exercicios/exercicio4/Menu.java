package fr.campusdual.exercicios.exercicio4;

import java.util.*;

/*
* --Escribe un programa que utilice la clase Dieta y despliegue un menú donde puedas añadir alimentos. El menú tendrá las siguientes opciones.
	-1. Crear/reiniciar dieta: crea o remplaza la dieta inicial
		-a. Sin limite
		-b. Por calorías
		-c. Por macronutrientes
		-d. Por datos personales
	-2. Mostrar información: muestra calorías y macronutrientes de la dieta
	-3. Agregar alimento: agrega un alimento a la dieta actual y añade ese alimento a la lista de alimentos disponible
		-a. Nuevo alimento
		-b. Alimento existente
	-4. Salir
* */
public class Menu {
    public static List<Food> foodList = new ArrayList<Food>(); // ArrayList para almacenar alimentos
    public static Scanner scanner= new Scanner(System.in);
    public static Diet noRestrictionDiet = new Diet();
    public static Diet caloriesRestrictionDiet =new Diet();
    public static Diet parameterRestrictionDiet = new Diet();
    public static Diet TMBRestrictionDiet = new Diet();
    public static Integer maxCalories =0;
    public static Integer maxFat =0;
    public static Integer maxCarbo =0;
    public static Integer maxProtein =0;
    public static final String ENGADIR_ALIMENTO ="1. Añadir alimento existente. ";
    public static final String CREAR_ALIMENTO ="2. Crear alimento. ";
    public static final String MOSTRAR_DIETA ="3. Ver dieta actual. ";
    public static final String SALIR ="5. Salir. ";
    public static final String ALIMENTO_ENGADIDO ="Alimento añadido a tu dieta.";
    public static final String INVALID_SELECTION ="Selección de alimento no válida.";





    public static void main(String[]args) throws Exception {


            menu();
            }
            public static void menu() throws Exception {
                Scanner scanner = new Scanner(System.in);
                boolean salir=false;
                while (!salir) {

                    System.out.println("Welcome to your Diet creator");


                    System.out.println("1. Crear alimento");
                    System.out.println("2. Crear Dieta");
                    System.out.println("3. Detalles de dieta actual sin restricciones");
                    System.out.println("4. Detalles de dieta actual con restricción de calorías");
                    System.out.println("5. Detalles de dieta actual con restricción de nutrientes");
                    System.out.println("6. Detalles de dieta actual con restricción por TMB");
                    System.out.println("7. Salir");
                    Integer input = scanner.nextInt();
                    switch (input) {
                        case 1:
                            foodCreator();
                        case 2:

                            dietCreator();

                        case 3:

                           showDietDetails(noRestrictionDiet);

                        case 4:

                            showDietDetails(caloriesRestrictionDiet);

                        case 5:

                            showDietDetails(parameterRestrictionDiet);

                        case 6:

                            showDietDetails(TMBRestrictionDiet);

                        case 7:
                            salir=true;
                        default:
                            break;

                    }
                }

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
        public static void dietCreator() throws Exception {
                    boolean salir = false;
                    while (!salir) {

                        System.out.println("Seleccione una opción de dieta: ");
                        System.out.println("1. Dieta sin límite de calorías.");
                        System.out.println("2. Dieta con límite de calorías.");
                        System.out.println("3. Dieta con límite de carbos, grasas y proteínas.");
                        System.out.println("4. Dieta con límite de calorías según tu TMB.");
                        System.out.println(SALIR);

                        Integer option = scanner.nextInt();

                        switch (option){
                            case 1:
                                noRestrictionDietMenu();
                            case 2:
                               modificarRestriccionCalorica();
                                caloriesRestrictionDiet();

                            case 3:
                                modificarRestriccionNutrientes();
                                nutrientRestrictionDiet();
                            case 4:
                                modificarParametrosTMB();
                                TMBRestrictionDiet();
                            case 5:
                                salir= true;
                                break;
                            default:
                                break;


                        }

                    }
                }

    private static void modificarParametrosTMB() {
        System.out.println("Introduzca su edad :");
        Integer age = scanner.nextInt();
        TMBRestrictionDiet.setAge(age);

        System.out.println("Introduzca su peso :");
        Integer weight = scanner.nextInt();
        TMBRestrictionDiet.setWeight(weight);
        System.out.println("Su peso es : "+ weight);

        System.out.println("Introduzca su altura :");
        Integer height = scanner.nextInt();
        TMBRestrictionDiet.setHeight(height);
        System.out.println("Su altura es : "+ height);

        System.out.println("¿Hombre o mujer? : ");
        System.out.println( "1. Hombre");
        System.out.println( "2. Mujer");
        Integer sexOption = scanner.nextInt();

        switch (sexOption){
            case 1 :
                TMBRestrictionDiet.setWomen(false);
                break;
            case 2:
                TMBRestrictionDiet.setWomen(true);
                break;
            default:
                System.out.println("Tienes que elegir un sexo. ");
                break;
        }

    }

    private static void TMBRestrictionDiet() throws Exception {
        boolean salir= false;

        while (!salir){

            System.out.println("Ha elegido una dieta con restricción calórica basada en su TMB");

            System.out.println("Su edad es : "+ TMBRestrictionDiet.getAge());
            System.out.println("Su peso es : "+ TMBRestrictionDiet.getWeight());
            System.out.println("Su altura es : "+ TMBRestrictionDiet.getHeight());
            System.out.println("Seleccione una opción : " );

            System.out.println(ENGADIR_ALIMENTO);
            System.out.println(CREAR_ALIMENTO);
            System.out.println(MOSTRAR_DIETA);
            System.out.println("4. Modificar valores corporales");
            System.out.println(SALIR);

            Integer TMBRestrictionDietOption = scanner.nextInt();

            switch (TMBRestrictionDietOption){
                case 1 :
                    TMBCaloriesRestrictionSelectFood();
                    break;
                case 2 :
                    foodCreator();
                    break;
                case 3:
                    showDietDetails(TMBRestrictionDiet);
                    break;
                case 4:
                    modificarParametrosTMB();
                    break;
                case 5:
                    salir=true;
                    break;
                default:
                    break;
            }
        }
    }
    public static void caloriesRestrictionDiet() throws Exception {
        boolean salir = false;

        while(!salir){

            System.out.println("Ha elegido una dieta con restricción calórica de, "+ maxCalories +
                    " , seleccione una opción: ");

            System.out.println(ENGADIR_ALIMENTO);
            System.out.println(CREAR_ALIMENTO);
            System.out.println(MOSTRAR_DIETA);
            System.out.println("4. Modificar restrición calórica");
            System.out.println(SALIR);

            Integer caloriesRestrictionDietMenuOption=scanner.nextInt();

            switch (caloriesRestrictionDietMenuOption){

                case 1:
                    caloriesRestrictionDiet=caloriesRestrictionSelectFood();
                    break;
                case 2:
                    foodCreator();
                    break;
                case 3:
                    showDietDetails(caloriesRestrictionDiet);
                    break;
                case 4:
                    modificarRestriccionCalorica();
                    break;
                case 5:
                    salir=true;
                    break;
                default:
                    break;
            }
        }
    }
    private static void modificarRestriccionCalorica(){

        System.out.println("Introduzca el valor de la restricción de calorías :");
        maxCalories = scanner.nextInt();
        System.out.println("Límite de calorías elegido : "+ maxCalories);
        caloriesRestrictionDiet.setMaxCalories(maxCalories);
    }

    private static void nutrientRestrictionDiet() throws Exception {
                boolean salir = false;

                while (!salir){

                    System.out.println("Ha elegido una dieta con restricciones de -Hidratos - : "+maxCarbo+
                            " , - Grasas - : "+ maxFat+ " , - Proteínas - : "+maxProtein+ " .");
                    System.out.println("Seleccione una opción : ");

                    System.out.println("1. Añadir alimento existente. ");
                    System.out.println("2. Crear alimento. ");
                    System.out.println("3. Ver dieta actual. ");
                    System.out.println("4. Modificar restriciónes");
                    System.out.println("5. Salir. ");
                    Integer nutrientRestrictionDietMenuOption=scanner.nextInt();

                    switch (nutrientRestrictionDietMenuOption){

                        case 1:
                            parameterRestrictionDiet=nutrientRestrictionSelectFood();
                            break;
                        case 2:
                            foodCreator();
                            break;
                        case 3:
                            showDietDetails(parameterRestrictionDiet);
                            break;
                        case 4:
                            modificarRestriccionNutrientes();
                            break;
                        case 5:
                            salir=true;
                            break;
                        default:
                            break;
                    }
                }
        }

    private static void modificarRestriccionNutrientes() {
        System.out.println("Introduzca el valor de la restricción de carbohidratos :");
        maxCarbo = scanner.nextInt();
        System.out.println("Límite de carbohidratos elegido : "+ maxCarbo);
        parameterRestrictionDiet.setMaxCarbs(maxCarbo);

        System.out.println("Introduzca el valor de la restricción de grasas :");
        maxFat = scanner.nextInt();
        System.out.println("Límite de grasas elegido : "+ maxFat);
        parameterRestrictionDiet.setMaxFats(maxFat);

        System.out.println("Introduzca el valor de la restricción de proteínas :");
        maxProtein = scanner.nextInt();
        System.out.println("Límite de proteínas elegido : "+ maxProtein);
        parameterRestrictionDiet.setMaxProtein(maxProtein);
    }
    private static Integer metabolismoBasal(Diet diet) throws Exception {
        Integer metabolismoBasal = 0;
        Integer foodCalories = diet.calculateTotalCalories(TMBRestrictionDiet.getFoodMap());
        if (diet.getWomen()==null){
            modificarParametrosTMB();
        }

        // Verifica si la persona es mujer (true) o no (false)
        if (diet.getWomen()) {
            // Fórmula para el metabolismo basal en mujeres
            metabolismoBasal = (int) (10 * (diet.getWeight()) + 6.25 * (diet.getHeight()) - 5 * (diet.getAge()) - 161);

            // Comprueba si las calorías de la dieta superan el metabolismo basal
            if (metabolismoBasal < foodCalories) {
                throw new Exception("Necesitas menos calorías en tu dieta debido a tu TMB");
            }
        }
        // Si la persona no es mujer (hombre)
        else if (!diet.getWomen()) {
            // Fórmula para el metabolismo basal en hombres
            metabolismoBasal = (int) (10 * (diet.getWeight()) + 6.25 * (diet.getHeight()) - 5 * (diet.getAge()) + 5);
        }

        // Comprueba nuevamente si las calorías de la dieta superan el metabolismo basal
        if (metabolismoBasal < foodCalories) {
            throw new Exception("Necesitas menos calorías en tu dieta debido a tu TMB");
        }

        return metabolismoBasal;
    }



    public static void noRestrictionDietMenu(){
        boolean salir = false;

                while(!salir){



                System.out.println("Ha elegido una dieta sin restricciones," +
                        " seleccione una opción: ");
                System.out.println("1. Añadir alimento existente. ");
                System.out.println("2. Crear alimento. ");
                System.out.println("3. Detalles de Dieta actual. ");
                System.out.println("4. Salir. ");
                Integer noRestrictionDietMenuOption = scanner.nextInt();

                switch (noRestrictionDietMenuOption){

                    case 1:
                        noRestrictionDiet=selectFood();
                        break;

                    case 2:
                        foodCreator();
                        break;
                    case 3:
                        showDietDetails(noRestrictionDiet);
                        break;
                    case 4:
                        salir=true;
                        break;
                    default:
                        break;
                }
                }
            }
    public static void showDietDetails(Diet diet){
        System.out.println(diet.toString());
}

    public static Diet selectFood(){

        if (foodList.isEmpty()){
                System.out.println("Todavía no hay alimentos en la lista, cree alguno : ");
                foodCreator();
            }

            Map<Food, Integer> dietFoodMap = new HashMap<Food, Integer>();
            Diet noRestrictionDietFromSelectFood= new Diet();

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
            int selectedFoodIndex = scanner.nextInt();
            scanner.nextLine(); // Consumir la línea en blanco después del nextInt()

            if (selectedFoodIndex == 0) {
                System.out.println("Saliendo del programa.");
            } else if(selectedFoodIndex >= 1 && selectedFoodIndex <= foodList.size()) {
                Food selectedFood = foodList.get(selectedFoodIndex - 1);

                System.out.print("Ingresa la cantidad en gramos de " + selectedFood.getName() + ": ");
                Integer grams = scanner.nextInt();

                // Agregar el alimento seleccionado como clave y la cantidad en gramos como valor al dietMap
                dietFoodMap.put(selectedFood, grams);
                noRestrictionDietFromSelectFood.setFoodMap(dietFoodMap);


                System.out.println(ALIMENTO_ENGADIDO);

            } else {
                System.out.println(INVALID_SELECTION);
            }

            return noRestrictionDietFromSelectFood;
        }
    public static Diet nutrientRestrictionSelectFood() throws Exception {


            if (foodList.isEmpty()){
                    System.out.println("Todavía no hay alimentos en la lista, cree alguno : ");
                    foodCreator();
                }

                Diet nutrientRestrictionDietFromSelectFood= new Diet();
                Map<Food, Integer> dietFoodMap = new HashMap<Food, Integer>();

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
                int selectedFoodIndex = scanner.nextInt();
                scanner.nextLine(); // Consumir la línea en blanco después del nextInt()

                if (selectedFoodIndex == 0) {
                    System.out.println("Saliendo del programa.");
                    salir=true;

                } else if(selectedFoodIndex >= 1 && selectedFoodIndex <= foodList.size()) {
                    Food selectedFood = foodList.get(selectedFoodIndex - 1);

                    System.out.print("Ingresa la cantidad en gramos de " + selectedFood.getName() + ": ");
                    Integer grams = scanner.nextInt();

                    // Agregar el alimento seleccionado como clave y la cantidad en gramos como valor al dietMap
                    dietFoodMap.put(selectedFood, grams);
                    overMaxParameter(dietFoodMap);
                    nutrientRestrictionDietFromSelectFood.addFood(selectedFood,grams);


                    System.out.println(ALIMENTO_ENGADIDO);

                } else {
                    System.out.println(INVALID_SELECTION);
                }
                }
               overMaxParameter(nutrientRestrictionDietFromSelectFood.getFoodMap());

                return nutrientRestrictionDietFromSelectFood;
            }
    public static void overMaxParameter(Map<Food, Integer> foodMap) throws Exception {
        Set<Food> claves = foodMap.keySet();
        Integer totalCarbs=0;
        Integer totalFat=0;
        Integer totalProtein=0;
        for (Food clave: claves){
            totalFat += (clave.getFats()*foodMap.get(clave));
            totalCarbs += (clave.getCarbos()*foodMap.get(clave));
            totalProtein += (clave.getProteins()*foodMap.get(clave));

            if (totalFat>maxFat){
                throw new Exception("The actual fat of this diet is over the maxFat," +
                        " here is the guilty : "+ clave.getName());
            }
            if (totalProtein>maxProtein){
                throw new Exception("The actual protein of this diet is over the maxProtein," +
                        " here is the guilty : "+ clave.getName());
            }
            if (totalCarbs>maxCarbo){
                throw new Exception("The actual carbs of this diet is over the maxCarbs," +
                        " here is the guilty : "+ clave.getName());
            }
        }
    }

    public static Diet caloriesRestrictionSelectFood() throws Exception {

                Map<Food, Integer> dietFoodMap = new HashMap<>();
                Diet caloriesRestrictionDietFromSelectFood=new Diet();
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


                System.out.print("Elige un alimento (ingresa el número correspondiente), o '0' para salir: ");
                int selectedFoodIndex = scanner.nextInt();
                scanner.nextLine(); // Consumir la línea en blanco después del nextInt()

                if (selectedFoodIndex == 0) {
                    System.out.println("Saliendo del programa.");
                    salir=true;
                } else if(selectedFoodIndex >= 1 && selectedFoodIndex <= foodList.size()) {
                    Food selectedFood = foodList.get(selectedFoodIndex - 1);

                    System.out.print("Ingresa la cantidad en gramos de " + selectedFood.getName() + ": ");
                    Integer grams = scanner.nextInt();

                    // Agregar el alimento seleccionado como clave y la cantidad en gramos como valor al dietMap
                    dietFoodMap.put(selectedFood, grams);
                    caloriesRestrictionDietFromSelectFood.setFoodMap(dietFoodMap);
                    caloriesRestrictionDietFromSelectFood.overMaxCalories(dietFoodMap,maxCalories);
                    caloriesRestrictionDiet=caloriesRestrictionDietFromSelectFood;


                    System.out.println(ALIMENTO_ENGADIDO);

                } else {
                    System.out.println(INVALID_SELECTION);
                }
                }

                return caloriesRestrictionDietFromSelectFood;
            }
    public static Diet TMBCaloriesRestrictionSelectFood() throws Exception {

                Map<Food, Integer> dietFoodMap = new HashMap<Food, Integer>();
                Diet TMBcaloriesRestrictionDietFromSelectFood=new Diet();
                boolean salir= false;
                while (!salir){

                System.out.println("0. Salir");
                    if (foodList.isEmpty()){
                        System.out.println("Todavía no hay alimentos guardados en la lista, vamos a crear alguno : ");
                        foodCreator();
                    }

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



                System.out.print("Elige un alimento (ingresa el número correspondiente), o '0' para salir: ");
                int selectedFoodIndex = scanner.nextInt();
                scanner.nextLine(); // Consumir la línea en blanco después del nextInt()

                if (selectedFoodIndex == 0) {
                    System.out.println("Saliendo del programa.");
                    salir=true;
                } else if(selectedFoodIndex >= 1 && selectedFoodIndex <= foodList.size()) {

                    Food selectedFood = foodList.get(selectedFoodIndex - 1);

                    System.out.print("Ingresa la cantidad en gramos de " + selectedFood.getName() + ": ");
                    Integer grams = scanner.nextInt();

                    // Agregar el alimento seleccionado como clave y la cantidad en gramos como valor al dietMap
                    dietFoodMap.put(selectedFood, grams);
                    TMBRestrictionDiet.overMaxCalories(dietFoodMap,metabolismoBasal(TMBRestrictionDiet));
                    TMBRestrictionDiet.setFoodMap(dietFoodMap);
                    maxCalories= metabolismoBasal(TMBRestrictionDiet);


                    System.out.println(ALIMENTO_ENGADIDO);

                } else {
                    System.out.println(INVALID_SELECTION);
                }
                }

                return TMBcaloriesRestrictionDietFromSelectFood;
            }
    }






