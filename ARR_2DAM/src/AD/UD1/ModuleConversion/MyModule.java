package ModuleConversion;

import java.io.Serializable;
import java.util.Objects;

public class MyModule implements Serializable {
    String moduleNames;
    int hours;
    double averageGrades;

    public MyModule(String moduleNames, int hours, double averageGrades) {
        this.moduleNames = moduleNames;
        this.hours = hours;
        this.averageGrades = averageGrades;
    }


    public String getModuleNames() {
        return moduleNames;
    }

    public void setModuleNames(String moduleNames) {
        this.moduleNames = moduleNames;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public double getAverageGrades() {
        return averageGrades;
    }

    public void setAverageGrades(double averageGrades) {
        this.averageGrades = averageGrades;
    }

    @Override
    public String toString() {
        return "ModuleConversion.Module{" +
                "moduleNames=" + (moduleNames) +
                ", hours=" + (hours) +
                ", averageGrades=" + (averageGrades) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyModule myModule = (MyModule) o;
        return getHours() == myModule.getHours() && Double.compare(getAverageGrades(), myModule.getAverageGrades()) == 0 && Objects.equals(getModuleNames(), myModule.getModuleNames());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModuleNames(), getHours(), getAverageGrades());
    }
}
