package diseno.sistemas.presentacion.principal;

import java.util.Observable;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import diseno.sistemas.dominio.mantenimiento.Usuario;
import diseno.sistemas.service.Service;
import diseno.sistemas.util.DatosEvent;
import diseno.sistemas.util.DatosListener;

public class Principal implements DatosListener{

	private Service service;
	private Usuario usuario;
	/**
	 * Launch the application.
	 */
	
	@SuppressWarnings("unused")
	public Principal() {
		super();
		cargarServicio();
		System.out.println(service.saludo());
		//System.out.println(service.getFechaSistema());
		LoginFrame login=new LoginFrame(this,service);
		
	}


	public void notificarCambios(DatosEvent datosEvent) {
		if(datosEvent.getOrigen().equals("InicioSesion") && datosEvent.getUsuario()!=null){
			System.out.println("Se ingreso con los datos del usuario "+datosEvent.getUsuario().getNombres());
			setUsuario(datosEvent.getUsuario());
			new BaseFrame(this,service);
		}
	}
	
	public void cargarServicio(){
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
	    service = (Service)beanFactory.getBean("httpInvokerProxy");
	}


	public Service getService() {
		return service;
	}


	public void setService(Service service) {
		this.service = service;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
