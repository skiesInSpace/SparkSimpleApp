package inteview_exercises;

public class TestInMethodReferenceTypes {

    public static void main(String[] args) {
        Car innerCar = new Car(null);
        Car car = new Car(innerCar);
        car.go(car);
        System.out.println();
    }
}

class Car{

    private Car innerCar;

    public Car(Car innerCar) {
        this.innerCar = innerCar;
    }

    public void go(Car car){
        car = null;
    }
}