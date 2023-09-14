package fr.campusdual.exercicios.exercicio5;

import java.util.ArrayList;
import java.util.List;

import static fr.campusdual.exercicios.exercicio5.Menu.*;

public class User {
    private List<Diet> dietList;
    private String name;
    private String password;
    private Integer weight;
    private Integer height;
    private Integer age;
    private boolean woman;

    public User(List<Diet> dietList, String name,String password, Integer weight, Integer height, Integer age,boolean woman) {
        this.dietList = dietList;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.password= password;
        this.woman = woman;
    }

    public User() {
        this.dietList = new ArrayList<>();
    }

    public List<Diet> getDietList() {
        return dietList;
    }

    public void setDietList(List<Diet> dietList) {
        this.dietList = dietList;
    }
    public void addDietToList(Diet diet){
        List<Diet>newDietList = actualUser.getDietList();
        newDietList.add(diet);
        setDietList(newDietList);
    }
    public void removeDietFromList(Diet diet){
        this.dietList.remove(diet);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public boolean isWoman() {
        return woman;
    }

    public void setWoman(boolean woman) {
        this.woman = woman;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login(){
        if (users.isEmpty()){
            System.out.println("No hay usuarios creados, sea usted el primero");
            userCreator();

        }else {


            boolean salir = false;
                while (!salir){
                    System.out.println("1. Entrar con mi usuario");
                    System.out.println("2. Crear un usuario");
                    System.out.println("3. Cerrar sesión");
                    System.out.println("4. Atrás");
                    Integer menuOption= Kb.nextInt();
                    switch (menuOption){
                        case 1:
                            User loginUser= newUser();

                            actualUser=userValidator(loginUser.name,loginUser.password);

                            if (actualUser== null){
                                System.out.println("Parece que no recuerdas los datos de tu cuenta, creemos una nueva :");
                                userCreator();
                            }
                            break;

                        case 2:
                            actualUser = userCreator();
                            break;
                        case 3:
                            actualUser=null;
                            salir=true;
                            break;
                        case 4:
                            salir=true;
                            break;
                        default:
                            break;
                    }
                }

    }
    }
    public User actualLogin(){
        System.out.println("Introduzca su nombre de usuario");
        String userName=Kb.nextLine();

        System.out.println("Introduzca su nombre contraseña");
        String userPassword=Kb.nextLine();

        for (User user : users) {
            if((user.name.equals(userName))&&(user.password.equals(userPassword))){
                actualUser=user;
            }
        }

       return actualUser;
    }
    public User userValidator(String userName, String userPassword) {
        int intentosNombre = 3;
        int intentosContraseña = 3;

        while (intentosNombre > 0) {
            boolean usuarioEncontrado = false;
            // Busca un usuario con el nombre de usuario especificado en la lista users
            for (User user : users) {
                if (user.getName().equals(userName)) {
                    // Se encontró un usuario con el nombre de usuario especificado
                    // Comprueba si la contraseña coincide
                    usuarioEncontrado = true;
                    while (intentosContraseña > 0) {
                        if (user.getPassword().equals(userPassword)) {
                            actualUser=user;
                            return user; // La contraseña coincide, devuelve el usuario
                        } else {
                            System.out.println("Contraseña incorrecta. Intentos restantes: " + (intentosContraseña - 1));
                            intentosContraseña--;
                            if (intentosContraseña == 0) {
                                System.out.println("Se han agotado los intentos para la contraseña.  ");
                                // Llama a userCreator() cuando se agoten los intentos de contraseña
                                return null;
                            } else {
                                System.out.println("Introduce tu contraseña de Usuario : ");
                                userPassword = Kb.nextLine();
                            }
                        }
                    }
                }
            }

            if (!usuarioEncontrado) {
                System.out.println("No se encontró un usuario con el nombre de usuario especificado. Intentos restantes: " + (intentosNombre - 1));
                intentosNombre--;
                if (intentosNombre == 0) {
                    System.out.println("Se han agotado los intentos para el nombre de usuario. ");
                    // Llama a userCreator() cuando se agoten los intentos de nombre de usuario
                    return null;
                } else {
                    System.out.println("Introduce tu nombre de Usuario : ");
                    userName = Kb.nextLine();
                }
            }
        }
        return null;
    }

    public Integer metabolismoBasal(User user){
        Integer metabolismoBasal = 0;

        if (user.isWoman()){
            metabolismoBasal = (int) (10 * (user.getWeight()) + 6.25 * (user.getHeight()) - 5 * (user.getAge()) - 161);

            modificarParametrosTMBActuales();
        }

        // Verifica si la persona es mujer (true) o no (false)
        if (!user.isWoman()) {
            // Fórmula para el metabolismo basal en mujeres
            metabolismoBasal = (int) (10 * (user.getWeight()) + 6.25 * (user.getHeight()) - 5 * (user.getAge()) + 5);

        }
        return metabolismoBasal;
    }
    public User userCreator(){
        System.out.println("-----------------------");
        System.out.println("Bienvenido al creador de Usuarios");
        User newUser = newUser();

        while (userExists(newUser)){

           newUser.setName(newUserName());

        }

        users.add(newUser);

        return newUser;
    }
    public boolean userExists(User user){
        boolean userExists = false;

        for (User savedUser:users){
            if (savedUser.getName().equals(user.getName())) {
                userExists = true;
            }
        }
        return userExists;
    }
    public String newUserName(){

        System.out.println("Introduzca el nombre de Usuario : ");
        String userName = Kb.nextLine();
        return userName;
    }

    /*public User userCreator() {

        System.out.println("-----------------------");
        System.out.println("Bienvenido al creador de Usuarios");

        boolean nombreDisponible = false;
        String userName = null;
        String userPassword=null;
        User newUser = newUser();
        while (!nombreDisponible) {

            // Verifica si el nombre de usuario ya existe en la lista
            boolean nombreExistente = false;
            for (User user : users) {
                if (user.name.equals(newUser.name)) {
                    nombreExistente = true;
                    break;
                }
            }

            if (nombreExistente) {
                System.out.println("Ya existe un usuario con ese nombre, elija otro : ");
                newUser.setName(Kb.nextLine());
            } else {
                nombreDisponible = true;
            }
        }
        users.add(newUser);

        actualUser=newUser;

        System.out.println("Usuario guardado en la base de datos");

        return newUser;
    }*/
    public User newUser(){
        System.out.println("Introduzca el nombre de Usuario : ");
        String userName = Kb.nextLine();
        System.out.println("Introduzca su contraseña : ");
        String userPassword = Kb.nextLine();
        User newUser = new User();
        newUser.setName(userName);
        newUser.setPassword(userPassword);

        return newUser;
    }
    public User userDeleter() {

        System.out.println("-----------------------");
        System.out.println("Bienvenido al borrador de Usuarios");
        if (actualUser.getName()==null){
            System.out.println("Para borrar su usuario debe hacer log in. ");
            login();
        }

        String userName = null;
        String userPassword = null;

        System.out.println("Introduce tu contraseña de Usuario : ");
        userPassword = Kb.nextLine();
        User userToRemove = null;
        if (actualUser.getPassword().equals(userPassword)) {
            userToRemove = actualUser;
            deleteUser(userToRemove);
        } else {
            int intentos = 3;
            while (intentos > 0) {
                System.out.println("Introduce tu contraseña de Usuario : ");
                userPassword = Kb.nextLine();

                if (userToRemove.getPassword().equals(userPassword)) {
                    users.remove(userToRemove);
                    System.out.println("Usuario eliminado exitosamente.");
                    return userToRemove; // Usuario eliminado
                } else {
                    intentos--;
                    if (intentos > 0) {
                        System.out.println("Contraseña incorrecta. Intentos restantes: " + intentos);
                    }else {
                        System.out.println("Contraseña incorrecta. Se han agotado los intentos.");
                        return null; // Sale del menú si se agotan los intentos
                    }
                }
            }
        }
        return userToRemove;
    }

    public User createUser(String userName, String userPassword){
        User newUser = new User();
        newUser.setName(userName);
        newUser.setPassword(userPassword);
        return newUser;
    }
    static void modificarParametrosTMBActuales() {
        System.out.println("Introduzca su edad :");
        Integer age = Kb.nextInt();
        actualUser.setAge(age);

        System.out.println("Introduzca su peso en kilos:");
        Integer weight = Kb.nextInt();
        actualUser.setWeight(weight);
        System.out.println("Su peso es : "+ weight);

        System.out.println("Introduzca su altura en centímetros:");
        Integer height = Kb.nextInt();
        actualUser.setHeight(height);
        System.out.println("Su altura es : "+ height);

        System.out.println("¿Hombre o mujer? : ");
        System.out.println( "1. Hombre");
        System.out.println( "2. Mujer");
        Integer sexOption = Kb.nextInt();

        switch (sexOption){
            case 1 :
                actualUser.setWoman(false);
                break;
            case 2:
                actualUser.setWoman(true);
                break;
            default:
                System.out.println("Tienes que elegir un sexo. ");
                break;
        }

    }


    public User deleteUser(User userToRemove) {

        users.remove(userToRemove);


        return userToRemove;
    }

    public void showUserDetails() {
        System.out.println(actualUser.toString());
    }
    public void showUsersDetails() {
        for (User user : users){
            System.out.println(user.toString());
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Información de Usuario :\n");

        sb.append("Nombre: ").append(this.getName()).append("\n");
        sb.append("Altura: ").append(this.getHeight()).append(" centímetros\n");
        sb.append("Peso: ").append(this.getWeight()).append(" kilogramos\n");
        sb.append("Edad: ").append(this.getAge()).append(" años\n");
        sb.append("Límite de calorías por metabolismo basal: ").append(this.metabolismoBasal(this)).append(" calorías\n");
        sb.append("--------------------------\n").toString();

        return sb.toString();
    }
}
