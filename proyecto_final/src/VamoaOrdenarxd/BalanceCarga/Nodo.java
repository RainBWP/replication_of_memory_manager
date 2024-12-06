
public class Nodo {
	public Proceso procesoo; 
	public Nodo sig;
	
	public Nodo(Proceso proceso) {
		this.procesoo=proceso;
		this.sig=null;
	}

	public Proceso getTask() {
		return procesoo;
	}

	public void setTask(Proceso proceso) {
		this.procesoo = proceso;
	}

	public Nodo getSig() {
		return sig;
	}

	public void setSig(Nodo sig) {
		this.sig = sig;
	}
}
