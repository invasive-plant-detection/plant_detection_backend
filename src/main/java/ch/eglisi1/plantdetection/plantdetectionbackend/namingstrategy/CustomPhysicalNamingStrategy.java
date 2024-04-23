package ch.eglisi1.plantdetection.plantdetectionbackend.namingstrategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy {

    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment context) {
        return name;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment context) {
        return name;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return convertWithPrefix(name, "T_");
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment context) {
        return convertWithPrefix(name, "SEQ_");
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return convertToUpperCase(name); // Just convert column names to upper case
    }

    private Identifier convertWithPrefix(Identifier identifier, String prefix) {
        if (identifier == null) {
            return null;
        }
        String newName = identifier.getText().toUpperCase();
        newName = prefix + newName;
        return Identifier.toIdentifier(newName);
    }

    private Identifier convertToUpperCase(Identifier identifier) {
        if (identifier == null) {
            return null;
        }
        return Identifier.toIdentifier(identifier.getText().toUpperCase());
    }
}

