//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.10.25 a las 05:13:44 PM CEST 
//


package ejemploPlugins.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipo_estado.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="tipo_estado"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="borrado"/&gt;
 *     &lt;enumeration value="vigente"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tipo_estado")
@XmlEnum
public enum TipoEstado {

    @XmlEnumValue("borrado")
    BORRADO("borrado"),
    @XmlEnumValue("vigente")
    VIGENTE("vigente");
    private final String value;

    TipoEstado(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoEstado fromValue(String v) {
        for (TipoEstado c: TipoEstado.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
