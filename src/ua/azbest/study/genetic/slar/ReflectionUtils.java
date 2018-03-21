package ua.azbest.study.genetic.slar;

/**
 * Created by AZbest on 21.10.2015.
 */

        import java.lang.reflect.GenericDeclaration;
        import java.lang.reflect.ParameterizedType;
        import java.lang.reflect.Type;
        import java.lang.reflect.TypeVariable;
        import java.util.Stack;

/**
 * Alex Tracer (c) 2009
 */
public class ReflectionUtils {

    /**
     * ��� ���������� ������ (��� ����������) ����������,
     * ����� ������� ��� �������������� ���� �� ��� ������� (����������� �������) � generic-�����������.
     *
     * @param actualClass     ������������� �����
     * @param genericClass    ����� (��� ���������), ��� �������� ������������ �������� ���������
     * @param parameterIndex  ����� ���������
     * @return                �����, ���������� ���������� � �������� parameterIndex � genericClass
     */
    public static Class getGenericParameterClass(final Class actualClass, final Class genericClass, final int parameterIndex) throws IllegalArgumentException {
        // ���������� ������ ���� genericClass �� �������� ������� actualClass.
        if (!genericClass.isAssignableFrom(actualClass) || genericClass.equals(actualClass)) {
            //throw new IllegalArgumentException(String.format("Class %s is not a superclass of %s.", genericClass.getName()/*, actualClass.getName()*/));
        }
        final boolean isInterface = genericClass.isInterface();

        // ��� ����� ����� �����, ��� �������� ���������������� ��������� ����� genericClass.
        // �� ����� ����������� ����� �� ��������, ���� �� ������ ������������ ��� �����.
        // � �������� �������� �� ����� ��������� � genericClasses ��� ������ - ��� ��� ����������� ��� ������ ����.

        // ����������� ������ - ������������ ��� ������ ����.
        Stack<ParameterizedType> genericClasses = new Stack<ParameterizedType>();

        // clazz - ������� ��������������� �����
        Class clazz = actualClass;

        while (true) {
            Type genericInterface = isInterface ? getGenericInterface(clazz, genericClass) : null;
            Type currentType = genericInterface != null ? genericInterface : clazz.getGenericSuperclass();

            boolean isParameterizedType = currentType instanceof ParameterizedType;
            if (isParameterizedType) {
                // ���� ������ - ����������������� �����, �� ���������� ��� - �������� �� ���������� ��� ������ ����.
                genericClasses.push((ParameterizedType) currentType);
            } else {
                // � �������� ���������� ������������������� �����. ��� ����� ����������� ����������������� ������ ����� ����������.
                genericClasses.clear();
            }
            // ���������, ����� �� �� ������� ������ ��� ���.
            Type rawType = isParameterizedType ? ((ParameterizedType) currentType).getRawType() : currentType;
            if (!rawType.equals(genericClass)) {
                // genericClass �� �������� ���������������� ��������� ��� �������� ������.
                // ����������� �� �������� ������.
                clazz = (Class) rawType;
            } else {
                // �� ��������� �� ������� ������. ���������������.
                break;
            }
        }

        // ������ ����� ������. ������ �� ����� ������, ������ ������ �� ��������������.
        Type result = genericClasses.pop().getActualTypeArguments()[parameterIndex];

        while (result instanceof TypeVariable && !genericClasses.empty()) {
            // ������ ��� �������� ����� ���-�� ���� �� ��������, ���������� ����.

            // �������� ������ ��������� � ��� ������, � ������� �� �����.
            int actualArgumentIndex = getParameterTypeDeclarationIndex((TypeVariable) result);
            // ����� ��������������� �����, ���������� �������������� � ����� ���������.
            ParameterizedType type = genericClasses.pop();
            // �������� ���������� � �������� ���������.
            result = type.getActualTypeArguments()[actualArgumentIndex];
        }

        if (result instanceof TypeVariable) {
            // �� ���������� �� ������ ����, �� ���� ��� ������ �������� �� ����� ������ �������.
            // ������������� ��-�� "Type erasure" ������ ����� ��� ��������� ����������.
            throw new IllegalStateException("Unable to resolve type variable " + result + "."
                    + " Try to replace instances of parametrized class with its non-parameterized subtype.");
        }

        if (result instanceof ParameterizedType) {
            // ��� �������� �������� �����������������.
            // �������� ���������� � ��� ����������, ��� ��� �� �����.
            result = ((ParameterizedType) result).getRawType();
        }

        if (result == null) {
            // Should never happen. :)
            throw new IllegalStateException("Unable to determine actual parameter type for "
                    + actualClass.getName() + ".");
        }

        if (!(result instanceof Class)) {
            // ������, ��� �������� - ������, ����������� ����, ��������� ��� ���-���-��, ��� �� �������� �������.
            throw new IllegalStateException("Actual parameter type for " + actualClass.getName() + " is not a Class.");
        }

        return (Class) result;
    }

    public static int getParameterTypeDeclarationIndex(final TypeVariable typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();

        // ���� ��� �������� ����� ���� ���������� ���� ������, ��� ��������� ������ ��� ��������.
        TypeVariable[] typeVariables = genericDeclaration.getTypeParameters();
        Integer actualArgumentIndex = null;
        for (int i = 0; i < typeVariables.length; i++) {
            if (typeVariables[i].equals(typeVariable)) {
                actualArgumentIndex = i;
                break;
            }
        }
        if (actualArgumentIndex != null) {
            return actualArgumentIndex;
        } else {
            throw new IllegalStateException("Argument " + typeVariable.toString() + " is not found in "
                    + genericDeclaration.toString() + ".");
        }
    }

    public static Type getGenericInterface(final Class sourceClass, final Class genericInterface) {
        Type[] types = sourceClass.getGenericInterfaces();
        for (Type type : types) {
            if (type instanceof Class) {
                if (genericInterface.isAssignableFrom((Class)type)) {
                    return type;
                }
            } else if (type instanceof ParameterizedType) {
                if (genericInterface.isAssignableFrom((Class) ((ParameterizedType) type).getRawType())) {
                    return type;
                }
            }
        }
        return null;
    }
}