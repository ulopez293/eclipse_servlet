$(document).ready(function() {
	jsf.ajax.addOnError(function(data)  
	{
	    if (data.responseCode == 901)  
	    {
	        if (window.confirm("La sesion ha expirado"))  
	        self.location.href = "/smem-web/j_spring_security_logout";
		}
		else
		    alert(data.responseText);
	});
});