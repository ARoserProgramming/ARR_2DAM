import java.io.Serializable;
import java.util.Objects;

public class Module implements Serializable {
    String moduleNames;
    int hours;
    double averageGrades;

    public Module(String moduleNames, int hours, double averageGrades) {
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
        return "Module{" +
                "moduleNames=" + (moduleNames) +
                ", hours=" + (hours) +
                ", averageGrades=" + (averageGrades) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return getHours() == module.getHours() && Double.compare(getAverageGrades(), module.getAverageGrades()) == 0 && Objects.equals(getModuleNames(), module.getModuleNames());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModuleNames(), getHours(), getAverageGrades());
    }
}
