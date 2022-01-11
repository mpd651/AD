<?xml version="1.0" encoding='ISO-8859-1'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <xsl:apply-templates />
        </html>
    </xsl:template>
    <xsl:template match='departamentos'>
        <html>
            <head>
                <title>LISTADO DE DEPARTAMENTOS</title>
            </head>
            <body> 
                <h1>LISTADO DE DEPARTAMENTOS</h1>
                <table border='1'>
                    <tr>
                        <td>
                            <b>Numero</b>
                        </td>
                        <td>
                            <b>Nombre</b>
                        </td>
                        <td>
                            <b>Localidad</b>
                        </td>
                    </tr>
                    <xsl:apply-templates />
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match='departamento'>
        <tr>
            <xsl:apply-templates />
        </tr>
    </xsl:template>
    <xsl:template match='numero|nombre'>
        <td>
            <xsl:apply-templates />
        </td>
    </xsl:template>

    <xsl:template match="empleados"></xsl:template>
	
    <xsl:template match='localidad'>
        <td>
            <xsl:apply-templates />
        </td>
    </xsl:template>
</xsl:stylesheet>