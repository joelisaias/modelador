package ec.edu.ug.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class GenericManagedBean extends JSFUtils implements Serializable {
	
   
	
	private static final long serialVersionUID = 1L;
	
	public String getDisplayString(String bundleName, String id) {
		return getDisplayString(bundleName, id, "");
	}

	public String getDisplayString(String bundleName, String id,
			Object... params) {
		return "";
	}
	
	protected PrintWriter getOutputWriter() throws IOException{
		return getHttpServletResponse().getWriter();
	}
}