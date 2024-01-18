package ch.eglisi1.plantdetection.plantdetectionbackend.namingstrategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy {

    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment context) {
        return convert(name);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment context) {
        return convert(name);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return convert(name);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment context) {
        return convert(name);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return convert(name);
    }

    private Identifier convert(Identifier identifier) {
        if (identifier == null) {
            return null;
        }

        String regex = "(?i)dbo$"; // Case-insensitive match for 'dbo' at the end of the string
        String newName = identifier.getText().replaceAll(regex, "").toUpperCase();
        newName = "T_" + newName; // Prefix with 'T_'
        return Identifier.toIdentifier(newName);
    }
}

