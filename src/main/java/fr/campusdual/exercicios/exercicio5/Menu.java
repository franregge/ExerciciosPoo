package fr.campusdual.exercicios.exercicio5;

import java.util.*;

import static fr.campusdual.exercicios.exercicio5.Day.selectDay;
import static fr.campusdual.exercicios.exercicio5.NutrientRestrictionDiet.nutrientRestrictionSelectFood;
import static fr.campusdual.exercicios.exercicio5.User.*;

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
    public static List<User> users= new ArrayList<>();
    public static Day day;
    public static Food food;
    public static Diet diet;
    public static List<Food> foodList = new ArrayList<>(); // ArrayList para almacenar alimentos
    public static List<Diet> dietList= new ArrayList<>();
    public static Scanner scanner= new Scanner(System.in);
    public static User actualUser;
    public static NoRestrictionDiet actualNoRestrictionDiet = new NoRestrictionDiet();
    public static CaloriesRestrictionDiet actualCaloriesRestrictionDiet =new CaloriesRestrictionDiet();
    public static NutrientRestrictionDiet actualParameterRestrictionDiet = new NutrientRestrictionDiet();
    public static TMBRestrictionDiet actualTMBRestrictionDiet = new TMBRestrictionDiet();
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


                System.out.println("1. Login");
                System.out.println("2. Crear Usuario");
                System.out.println("3. Eliminar Usuario");
                System.out.println("4. Crear alimento");
                System.out.println("5. Crear Dieta");
                System.out.println("6. Detalles de dieta actual sin restricciones");
                System.out.println("7. Detalles de dieta actual con restricción de calorías");
                System.out.println("8. Detalles de dieta actual con restricción de nutrientes");
                System.out.println("9. Detalles de dieta actual con restricción por TMB");
                System.out.println("0. Salir");
                Integer input = scanner.nextInt();
                switch (input) {
                    case 1:
                        actualUser.login();
                    case 2:
                        actualUser.userCreator();
                    case 3:
                        actualUser.userDeleter();
                    case 4:
                        Food.foodCreator();
                    case 5:
                        Diet.dietCreator();
                    case 6:
                        if(actualNoRestrictionDiet==null){
                            System.out.println("Todavía no has seleccionado ninguna dieta de este tipo");
                            break;
                        }
                       actualNoRestrictionDiet.toString();

                    case 7:
                        if(actualCaloriesRestrictionDiet==null){
                            System.out.println("Todavía no has seleccionado ninguna dieta de este tipo");
                            break;
                        }
                        actualCaloriesRestrictionDiet.toString();
                    case 8:
                        if(actualParameterRestrictionDiet==null){
                            System.out.println("Todavía no has seleccionado ninguna dieta de este tipo");
                            break;
                        }
                        actualParameterRestrictionDiet.toString();
                    case 9:
                        if(actualTMBRestrictionDiet==null){
                            System.out.println("Todavía no has seleccionado ninguna dieta de este tipo");
                            break;
                        }
                        actualTMBRestrictionDiet.toString();
                    case 0:
                        salir=true;
                        break;
                    default:
                        break;
                }
            }
        }
    /*public static void foodCreator(){
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

            }*/
    /*public static void dietCreator() throws Exception {
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
                                modificarParametrosTMBActuales();
                                TMBRestrictionDiet();
                            case 5:
                                salir= true;
                                break;
                            default:
                                break;


                        }

                    }
                }*/


    /*static void TMBRestrictionDiet() throws Exception {
        boolean salir= false;

        while (!salir){

            System.out.println("Ha elegido una dieta con restricción calórica basada en su TMB");

            System.out.println("Su edad es : "+ user.getAge());
            System.out.println("Su peso es : "+ user.getWeight());
            System.out.println("Su altura es : "+ user.getHeight());
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
                    food.foodCreator();
                    break;
                case 3:
                    actualTMBRestrictionDiet.toString();
                    break;
                case 4:
                    user.modificarParametrosTMBActuales();
                    break;
                case 5:
                    salir=true;
                    break;
                default:
                    break;
            }
        }
    }*/
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
                    actualCaloriesRestrictionDiet=caloriesRestrictionSelectFood();
                    break;
                case 2:
                    Food.foodCreator();
                    break;
                case 3:
                    actualCaloriesRestrictionDiet.toString();
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
    static void modificarRestriccionCalorica(){

        System.out.println("Introduzca el valor de la restricción de calorías :");
        maxCalories = scanner.nextInt();
        System.out.println("Límite de calorías elegido : "+ maxCalories);
        actualCaloriesRestrictionDiet.setMaxCalories(maxCalories);
    }

    static void nutrientRestrictionDiet() {
                boolean salir = false;

                while (!salir){

                    System.out.println("Ha elegido una dieta con restricciones de -Hidratos - : "+maxCarbo+
                            " , - Grasas - : "+ maxFat+ " , - Proteínas - : "+maxProtein+ " .");
                    System.out.println("Seleccione una opción : ");

                    System.out.println("1. Seleccionar una dieta Existente. ");
                    System.out.println("2. Crear una Dieta. ");
                    System.out.println("3. Modificar la dieta actual. ");
                    System.out.println("4. Crear alimento. ");
                    System.out.println("5. Ver dieta actual. ");
                    System.out.println("6. Modificar restriciónes");
                    System.out.println("7. Salir. ");
                    Integer nutrientRestrictionDietMenuOption=scanner.nextInt();

                    switch (nutrientRestrictionDietMenuOption){

                        case 1:
                            actualParameterRestrictionDiet=NutrientRestrictionDiet.selectNutrientDiet();
                            break;
                        case 2:
                            actualParameterRestrictionDiet=nutrientRestrictionSelectFood();
                            break;
                         case 3:
                            actualParameterRestrictionDiet=nutrientRestrictionSelectFood();
                            break;
                        case 4:
                            Food.foodCreator();
                            break;
                        case 5:
                            actualParameterRestrictionDiet.toString();
                            break;
                        case 6:
                            NutrientRestrictionDiet.modificarRestriccionNutrientes(actualParameterRestrictionDiet);
                            break;
                        case 7:
                            salir=true;
                            break;
                        default:
                            break;
                    }
                }
        }

    static void modificarRestriccionNutrientes() {
        System.out.println("Introduzca el valor de la restricción de carbohidratos :");
        maxCarbo = scanner.nextInt();
        System.out.println("Límite de carbohidratos elegido : "+ maxCarbo);
        actualParameterRestrictionDiet.setMaxCarbs(maxCarbo);

        System.out.println("Introduzca el valor de la restricción de grasas :");
        maxFat = scanner.nextInt();
        System.out.println("Límite de grasas elegido : "+ maxFat);
        actualParameterRestrictionDiet.setMaxFats(maxFat);

        System.out.println("Introduzca el valor de la restricción de proteínas :");
        maxProtein = scanner.nextInt();
        System.out.println("Límite de proteínas elegido : "+ maxProtein);
        actualParameterRestrictionDiet.setMaxProtein(maxProtein);
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
                        actualNoRestrictionDiet=Diet.selectFood();
                        break;

                    case 2:
                        Food.foodCreator();
                        break;
                    case 3:
                        actualNoRestrictionDiet.toString();
                        break;
                    case 4:
                        salir=true;
                        break;
                    default:
                        break;
            }
        }
    }
    /*public static NoRestrictionDiet selectFood(){

        if (foodList.isEmpty()){
                System.out.println("Todavía no hay alimentos en la lista, cree alguno : ");
                Food.foodCreator();
            }

            Map<Food, Integer> dietFoodMap = new HashMap<>();
            NoRestrictionDiet noRestrictionDietFromSelectFood= new NoRestrictionDiet();
            System.out.println("Escribe un nombre para tu dieta sin restricciones");
            noRestrictionDietFromSelectFood.setName(scanner.nextLine());

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
                noRestrictionDietFromSelectFood.setFoodMap(dietFoodMap,selectedDay);


                System.out.println(ALIMENTO_ENGADIDO);

            } else {
                System.out.println(INVALID_SELECTION);
            }
            dietList.add(noRestrictionDietFromSelectFood);

            return noRestrictionDietFromSelectFood;
        }*/
    /*public static NutrientRestrictionDiet nutrientRestrictionSelectFood() {


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
                    foodMap.put(selectedFood, grams);
                    salir=nutrientRestrictionDietFromSelectFood.overMaxParameter(foodMap);
                    nutrientRestrictionDietFromSelectFood.addFood(selectedFood,grams,selectedDay);


                    System.out.println(ALIMENTO_ENGADIDO);

                } else {
                    System.out.println(INVALID_SELECTION);
                }
                }

                return nutrientRestrictionDietFromSelectFood;
            }*/
   /* public static boolean overMaxParameter(Map<Food, Integer> foodMap) throws Exception {
        Set<Food> claves = foodMap.keySet();
        boolean overMaxParameter=false;
        Integer totalCarbs=0;
        Integer totalFat=0;
        Integer totalProtein=0;
        for (Food clave: claves){
            totalFat += (clave.getFats()*foodMap.get(clave));
            totalCarbs += (clave.getCarbos()*foodMap.get(clave));
            totalProtein += (clave.getProteins()*foodMap.get(clave));

            if (totalFat>maxFat){
                System.out.println("The actual fat of this diet is over the maxFat," +
                        " here is the guilty : "+ clave.getName());
                overMaxParameter=true;
            }
            if (totalProtein>maxProtein){
                System.out.println("The actual protein of this diet is over the maxProtein," +
                        " here is the guilty : "+ clave.getName());
                overMaxParameter=true;
            }
            if (totalCarbs>maxCarbo){
                System.out.println("The actual carbs of this diet is over the maxCarbs," +
                        " here is the guilty : "+ clave.getName());
                overMaxParameter=true;
            }
        }
        return overMaxParameter;
    }*/

    public static CaloriesRestrictionDiet caloriesRestrictionSelectFood() {

                Map<Food, Integer> dietFoodMap = new HashMap<>();
                CaloriesRestrictionDiet caloriesRestrictionDietFromSelectFood=new CaloriesRestrictionDiet();
                System.out.println("Ingresa el número máximo de calorías diarias que desesas: ");

                caloriesRestrictionDietFromSelectFood.setMaxCalories(scanner.nextInt());

                Day selectedDay = selectDay();

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
                    //Validamos el dietFoodMap con el overMaxCalories() antes de agregarlo a la Dieta
                    salir=caloriesRestrictionDietFromSelectFood.overMaxCalories(dietFoodMap,maxCalories);

                    caloriesRestrictionDietFromSelectFood.setFoodMap(dietFoodMap,selectedDay);


                    System.out.println(ALIMENTO_ENGADIDO);

                } else {
                    System.out.println(INVALID_SELECTION);
                }
                }

                return caloriesRestrictionDietFromSelectFood;
            }
    public static TMBRestrictionDiet TMBCaloriesRestrictionSelectFood() {

                TMBRestrictionDiet TMBcaloriesRestrictionDietFromSelectFood=new TMBRestrictionDiet();
                maxCalories= actualUser.metabolismoBasal(actualUser);
                Day selectedDay=selectDay();

                boolean salir= false;
                while (!salir){

                Map<Food, Integer> dayFoodMap = new HashMap<>();


                    System.out.println("0. Salir");
                    if (foodList.isEmpty()){
                        System.out.println("Todavía no hay alimentos guardados en la lista, vamos a crear alguno : ");
                        Food.foodCreator();
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
                    dayFoodMap.put(selectedFood, grams);
                    TMBcaloriesRestrictionDietFromSelectFood.overMaxCalories(dayFoodMap, actualUser.metabolismoBasal(actualUser));
                    TMBcaloriesRestrictionDietFromSelectFood.setFoodMap(dayFoodMap,selectedDay);



                    System.out.println(ALIMENTO_ENGADIDO);

                } else {
                    System.out.println(INVALID_SELECTION);
                }
                }
                dietList.add(TMBcaloriesRestrictionDietFromSelectFood);

                return TMBcaloriesRestrictionDietFromSelectFood;
            }
    }






