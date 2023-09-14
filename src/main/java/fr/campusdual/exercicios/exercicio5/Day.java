package fr.campusdual.exercicios.exercicio5;

import static fr.campusdual.exercicios.exercicio5.Menu.scanner;

public enum Day {
    MONDAY(1,"Lunes"),    // Lunes
    TUESDAY(2,"Martes"),   // Martes
    WEDNESDAY(3,"Miércoles"), // Miércoles
    THURSDAY(4,"Jueves"),  // Jueves
    FRIDAY(5,"Viernes"),    // Viernes
    SATURDAY(6,"Sábado"),  // Sábado
    SUNDAY(6,"Domingo")     // Domingo
    ;

    private Integer position;
    private String name;

    Day(Integer position, String name) {
    }
    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
    public static Day getDayByPosition(Day[] days, Integer dayOption) {
        for (Day day : days) {
            if (day.getPosition().equals(dayOption)) {
                return day;
            }
        }
        return null; // Retornar null si no se encuentra un día con esa posición
    }
    public static Day selectDay() {


        do {
            System.out.println("Seleccione el día de la semana. ");
            Day[] days = Day.values();

            // Recorrer los días de la semana usando un bucle for
            for (Day day : days) {
                System.out.println(day.getPosition() + ". " + day.getName());
            }

            int selectedPosition = scanner.nextInt();

            // Verificar si el día seleccionado está en el arreglo days
            boolean isValidSelection = false;
            for (Day day : days) {
                if (day.getPosition() == selectedPosition) {
                    isValidSelection = true;
                    break;
                }
            }

            if (!isValidSelection) {
                System.out.println("Opción no válida. Por favor, ingresa un número válido.");
            } else {
                // Obtener el día seleccionado
                Day selectedDay = getDayByPosition(days,selectedPosition);
                System.out.println("Día seleccionado: " + selectedDay.getName());
                return selectedDay;
            }
        } while (true); // Repetir hasta que se seleccione un día válido
    }
}
