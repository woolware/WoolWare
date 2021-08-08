package wool.ware.client.arraylist;

import java.util.ArrayList;

import wool.ware.client.module.Module;

public class ArrayListManager {
    private ArrayList<ArrayMember> arrayMembers = new ArrayList<>();

    public void addArray(Module module) {
        getArrayMembers().add(new ArrayMember(module));
    }

    public ArrayList<ArrayMember> getArrayMembers() {
        return arrayMembers;
    }
    public boolean isArrayMember(Module module) {
        return getArrayMembers().stream().filter(arrayMember -> arrayMember.getModule() == module).toArray().length > 0;
    }
}
