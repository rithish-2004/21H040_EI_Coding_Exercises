package Exercise1_Design_Patterns.Behavioural.Command_Pattern;
public interface Device {
    void turnOn();
    void turnOff();
    void setTemperature(int temperature);
    void lock();
    void unlock();
}

class Light implements Device {
    @Override
    public void turnOn() {
        System.out.println("Light is ON");
    }

    @Override
    public void turnOff() {
        System.out.println("Light is OFF");
    }

    @Override
    public void setTemperature(int temperature) {
        throw new UnsupportedOperationException("Light does not support temperature control.");
    }

    @Override
    public void lock() {
        throw new UnsupportedOperationException("Light does not support locking.");
    }

    @Override
    public void unlock() {
        throw new UnsupportedOperationException("Light does not support unlocking.");
    }
}

class Thermostat implements Device {

    @Override
    public void turnOn() {
        System.out.println("Thermostat is ON");
    }

    @Override
    public void turnOff() {
        System.out.println("Thermostat is OFF");
    }

    @Override
    public void setTemperature(int temperature) {
        System.out.println("Thermostat temperature set to " + temperature + "°C");
    }

    @Override
    public void lock() {
        throw new UnsupportedOperationException("Thermostat does not support locking.");
    }

    @Override
    public void unlock() {
        throw new UnsupportedOperationException("Thermostat does not support unlocking.");
    }
}

class DoorLock implements Device {
    @Override
    public void turnOn() {
        System.out.println("DoorLock is ON");
    }

    @Override
    public void turnOff() {
        System.out.println("DoorLock is OFF");
    }

    @Override
    public void setTemperature(int temperature) {
        throw new UnsupportedOperationException("DoorLock does not support temperature control.");
    }

    @Override
    public void lock() {
        System.out.println("Door is LOCKED");
    }

    @Override
    public void unlock() {
        System.out.println("Door is UNLOCKED");
    }
}
