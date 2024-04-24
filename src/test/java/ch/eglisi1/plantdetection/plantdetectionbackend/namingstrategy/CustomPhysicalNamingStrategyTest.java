package ch.eglisi1.plantdetection.plantdetectionbackend.namingstrategy;

import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.hibernate.boot.model.naming.Identifier;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

class CustomPhysicalNamingStrategyTest {
    @Mock
    private JdbcEnvironment jdbcEnvironment;

    private CustomPhysicalNamingStrategy namingStrategy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        namingStrategy = new CustomPhysicalNamingStrategy();
    }

    @Test
    public void testToPhysicalTableName() {
        Identifier original = Identifier.toIdentifier("myTable");
        Identifier transformed = namingStrategy.toPhysicalTableName(original, jdbcEnvironment);
        Assertions.assertEquals("T_MYTABLE", transformed.getText());
    }

    @Test
    public void testToPhysicalSequenceName() {
        Identifier original = Identifier.toIdentifier("mySequence");
        Identifier transformed = namingStrategy.toPhysicalSequenceName(original, jdbcEnvironment);
        Assertions.assertEquals("SEQ_MYSEQUENCE", transformed.getText());
    }

    @Test
    public void testToPhysicalColumnName() {
        String column = "myColumn";
        Identifier original = Identifier.toIdentifier(column);
        Identifier transformed = namingStrategy.toPhysicalColumnName(original, jdbcEnvironment);
        Assertions.assertEquals(column.toUpperCase(), transformed.getText());
    }

    @Test
    public void testNullIdentifierReturnsNull() {
        Assertions.assertNull(namingStrategy.toPhysicalTableName(null, jdbcEnvironment));
        Assertions.assertNull(namingStrategy.toPhysicalSequenceName(null, jdbcEnvironment));
        Assertions.assertNull(namingStrategy.toPhysicalColumnName(null, jdbcEnvironment));
    }
}