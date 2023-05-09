//package sk.tuke.gamestudio;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import sk.tuke.gamestudio.core.Field;
//
//class FieldTest {
//
//    @Test
//    @DisplayName("Test fieldSize s platným vstupom")
//    public void testFieldSizeValid() {
//        System.setIn(new java.io.ByteArrayInputStream("8\n".getBytes()));
//        int size = Field.fieldSize();
//        Assertions.assertEquals(8, size);
//    }
//
//
//
//    @Test
//    @DisplayName("Test fieldSize s neplatným vstupom")
//    public void testFieldSizeInvalid() {
//        System.setIn(new java.io.ByteArrayInputStream("3\n4\n26\n56\n1\n10\n".getBytes()));
//        int size = Field.fieldSize();
//        Assertions.assertEquals(10, size);
//    }
//
//    @Test
//    @DisplayName("Testovanie funkcie getSize na vrátenie hodnoty ")
//    public void testGetSize() {
//        Field.getSize();
//        int size = Field.getSize();
//        Assertions.assertEquals(10, size);
//    }
//}
