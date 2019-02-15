package Model;

public class LevelSingleton {
        // Static member holds only one instance of the
        // SingletonExample class
        private static LevelSingleton singletonInstance;

        // SingletonExample prevents any other class from instantiating
        private LevelSingleton() {
        }
        // Providing Global point of access
        public static LevelSingleton getSingletonInstance() {
            if (null == singletonInstance) {
                singletonInstance = new LevelSingleton();
            }
            return singletonInstance;
        }
        public void printSingleton(){
            System.out.println("demoMethod for singleton");
        }
    }
