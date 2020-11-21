package co.ho.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface Model {
	public String action(ServletRequest request, ServletResponse response) throws ServletException, IOException;
}
