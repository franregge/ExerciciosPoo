package fr.campusdual.exercicios.exercicio4;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    private Map<Food,Integer> foodMap;
    private Integer maxCalories;
    private Integer maxFats;
    private Integer maxCarbs;
    private Integer maxProtein;
    private Boolean women;
    private Integer age;
    private Integer height;
    private Integer weight;

    //crea una dieta con un máximo de calorías,
    // en cuanto se supera ese máximo cuando se adjunta un alimento se despliega un error
    public Diet(Map<Food,Integer> foodMap, Integer maxCalories) throws Exception {
        this.foodMap = foodMap;
        this.maxCalories= maxCalories;
        overMaxCalories(foodMap,maxCalories);

    }
    public Diet(){
        this.foodMap = new HashMap<>();

    }
    public Diet(Map<Food,Integer> foodMap,Integer maxFats, Integer maxCarbs, Integer maxProtein){
        this.foodMap=foodMap;
        this.maxCarbs= maxCarbs;
        this.maxFats= maxFats;
        this.maxProtein = maxProtein;

    }
    //crea una dieta con un máximo de estos tres valores,
    // si se supera alguno de ellos cuando se adjunta un alimento se indicara qué parámetro
    // se pasa y desplegará un error
    public Diet(Map<Food,Integer> foodMap, Boolean women, Integer age, Integer height, Integer weight){
        this.foodMap = foodMap;
        this.women=women;
        this.age=age;
        this.height=height;
        this.weight=weight;

    }

    public Integer getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(Integer maxCalories) {
        this.maxCalories = maxCalories;
    }

    public Integer getMaxFats() {
        return maxFats;
    }

    public void setMaxFats(Integer maxFats) {
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

    public Boolean getWomen() {
        return women;
    }

    public void setWomen(Boolean women) {
        this.women = women;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    private Integer metabolismoBasal(Diet diet) throws Exception {
        Integer metabolismoBasal = 0;
        Integer foodCalories = diet.calculateTotalCalories(diet.foodMap);

        // Verifica si la persona es mujer (true) o no (false)
        if (diet.women == true) {
            // Fórmula para el metabolismo basal en mujeres
            metabolismoBasal = (int) (10 * (diet.weight) + 6.25 * (diet.height) - 5 * (diet.age) - 161);

            // Comprueba si las calorías de la dieta superan el metabolismo basal
            if (metabolismoBasal < foodCalories) {
                throw new Exception("Necesitas menos calorías en tu dieta debido a tu TMB");
            }
        }
        // Si la persona no es mujer (hombre)
        else if (diet.women == false) {
            // Fórmula para el metabolismo basal en hombres
            metabolismoBasal = (int) (10 * (diet.weight) + 6.25 * (diet.height) - 5 * (diet.age) + 5);
        }

        // Comprueba nuevamente si las calorías de la dieta superan el metabolismo basal
        if (metabolismoBasal < foodCalories) {
            throw new Exception("Necesitas menos calorías en tu dieta debido a tu TMB");
        }

        return metabolismoBasal;
    }


    //Calcula el metabolismo basal de la persona y lo asigna como máximo de calorías
    // en la dieta

    //CALCULAR METABOLISMO BASAL
           // E = edad
    //A = altura en centímetros
          //  P = peso en kilos
    //Fórmula Hombres: TMB = 10P + 6,25A – 5E + 5
    //Fórmula Mujeres: TMB = 10P + 6,25A – 5E – 161




    public Diet(Map<Food,Integer> foodMap) {
        this.foodMap = foodMap;
    }

    public Map<Food,Integer> getFoodMap() {
        return foodMap;
    }
    public void overMaxCalories(Map<Food,Integer>foodMap,Integer maxCalories) throws Exception {

        Integer actualDietCalories =calculateTotalCalories(foodMap);
        if (actualDietCalories>maxCalories){
            throw new Exception ("Your diet cant be over "+maxCalories+". Yours : "+actualDietCalories);

        }

    }
    public void setFoodMap(Map<Food,Integer> foodMap) {
        this.foodMap = foodMap;
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
    public void addFood(Food food,Integer weight) throws Exception {
        if (this.foodMap.containsKey(food)){
            throw new Exception("You cant repeat a food in a diet");
        }else {
            foodMap.put(food,weight);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Alimentos en la Dieta:\n");

        for (Food food : this.foodMap.keySet()) {
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
        if(this.women!=null){
            if (this.women==true) {
                sb.append("Esta Dieta está pensada para una mujer, de peso : "
                        +this.weight+" , altura: "+this.height+" y edad : "+this.age);
            }
            if (this.women==false){
                sb.append("Esta dieta está pensada para un hombre de peso : "
                        +this.weight+" , altura: "+this.height+" y edad : "+this.age + " .");
            }

        }
        if (!this.foodMap.isEmpty()){
            sb.append(" Calorías totales de la Dieta : ").append(calculateTotalCalories(foodMap).toString());

        }
        return sb.toString();
    }
}
