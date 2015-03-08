<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="2.0">
	
	<xsl:param name="createDate" />
	
	<xsl:template match="/">
		<newMessage>
			<createDate><xsl:value-of select="$createDate" /></createDate>
			<camel><xsl:value-of select="//camel/camelVersion" /></camel>
			<activemq><xsl:value-of select="//activeMQ/activeMQVersion" /></activemq>
		</newMessage>
	</xsl:template>

</xsl:stylesheet>