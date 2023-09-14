package fr.campusdual.exercicios.exercicio5;

import java.util.Map;

import static fr.campusdual.exercicios.exercicio5.Menu.*;

public class TMBRestrictionDiet extends Diet{
    private Integer maxCalories;

    public TMBRestrictionDiet(Map<Day, Map<Food, Integer>> dietMap,  Integer maxCalories) {
        super(dietMap);
        this.maxCalories=maxCalories;
    }

    public TMBRestrictionDiet() {
        super();
    }



    public Integer getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(Integer maxCalories) {
        this.maxCalories = maxCalories;
    }
    static void TMBRestrictionDietMenu() {
        boolean salir= false;

        while (!salir){

            System.out.println("Ha elegido una dieta con restricción calórica basada en su TMB");

            System.out.println("Su edad es : "+ actualUser.getAge());
            System.out.println("Su peso es : "+ actualUser.getWeight());
            System.out.println("Su altura es : "+ actualUser.getHeight());
            System.out.println("Seleccione una opción : " );

            System.out.println(ENGADIR_ALIMENTO);
            System.out.println(CREAR_ALIMENTO);
            System.out.println(MOSTRAR_DIETA);
            System.out.println("4. Modificar valores corporales");
            System.out.println(SALIR);

            Integer TMBRestrictionDietOption = scanner.nextInt();

            switch (TMBRestrictionDietOption){
                case 1 :
                    actualTMBRestrictionDiet=TMBCaloriesRestrictionSelectFood();
                    break;
                case 2 :
                    Food.foodCreator();
                    break;
                case 3:
                    showDietDetails(actualTMBRestrictionDiet);
                    break;
                case 4:
                    User.modificarParametrosTMBActuales();
                    break;
                case 5:
                    salir=true;
                    break;
                default:
                    break;
            }
        }
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Alimentos en la Dieta:\n");
        Day[] days = Day.values();

        for (Day day : days) {
        for (Food food : this.getFoodMap(day).keySet()) {
            sb.append("Nombre: ").append(food.getName()).append("\n");
            sb.append("Carbohidratos: ").append(food.getCarbos()).append(" gramos\n");
            sb.append("Grasas: ").append(food.getFats()).append(" gramos\n");
            sb.append("Proteínas: ").append(food.getProteins()).append(" gramos\n");
            sb.append("Calorías (para 100 gramos): ").append(food.getCalories(100)).append(" calorías\n");
            sb.append("--------------------------\n");

        }
        if (this.maxCalories!=null) {
            sb.append("Calorías máximas : " + this.maxCalories);
        }

        if (!this.getFoodMap(day).isEmpty()){
            sb.append(" Calorías totales de la Dieta : ").append(calculateTotalCalories(getFoodMap(day)).toString());

        }
        }
        return sb.toString();
    }


}
