
package controladores;

enum Casilla {LIBRE,JUGADOR1,JUGADOR2};
public class Conecta4 {

    public Casilla[][] tablero;  // [fila][columna]
    private int numFil, numCol;
    public int jugador; //controla el turno del jugador siguiente
    
   

    public Conecta4() {
        this.numFil = 7;
        this.numCol = 8;
        this.jugador= 2;
        tablero = new Casilla[numFil][numCol];
        // crea las celdas
        for (int f = 0; f < numFil; f++)
            for (int c = 0; c < numCol; c++)
                tablero[f][c] = Casilla.LIBRE;
    }

    public int getNumFilas() {
        return numFil;
    }

    public int getNumColumnas() {
        return numCol;
    }
    
    public Casilla getCasilla(int c, int f) {
        return tablero[f][c];
    }
        

    public int ponFicha(int c) {
        
        int f=hayEspacio(c);
        if(f!=-1)
        {
            if(jugador==1)
            {
                tablero[f][c]=Casilla.JUGADOR1;
                jugador=2;
            }
            else
            {
                tablero[f][c]=Casilla.JUGADOR2;
                jugador=1;
            }
        }
        return f;
       
    }
    
    public void vacia() {
           for (int f = 0; f < numFil; f++)
            for (int c = 0; c < numCol; c++)
                tablero[f][c] = Casilla.LIBRE;
    }
    
    public int victoria(int f, int c){
        if (!columnaValida(c))
            return -1;
        if (!filaValida(f))
            return -1;
 
        Casilla aBuscar=tablero[f][c];
        
        //comprobamos si en la columna hay 4 en linea
        int fIni=f+1;
        int enLinea=1;
        while(filaValida(fIni)&&(tablero[fIni][c]==aBuscar))
        {
            enLinea++;
            fIni++;
        }
        fIni=f-1;
        while(filaValida(fIni)&&(tablero[fIni][c]==aBuscar))
        {
            enLinea++;
            fIni--;
        }
        
        
        
        if(enLinea>=4)	
            return 1;
       
        //comprobamos que en la fila hay 4 en linea
        int cIni=c+1;
        enLinea=1;
        
        while(columnaValida(cIni)&&(tablero[f][cIni]==aBuscar))
        {
            enLinea++;
            cIni++;
        }
        cIni=c-1;
        while(columnaValida(cIni)&&(tablero[f][cIni]==aBuscar))
        {
            enLinea++;
            cIni--;
        }
        
         if(enLinea>=4)	
            return 1;
       
        //comprobamos que en diagonal hay 4 en linea
        cIni=c+1;
        fIni=f+1;
        enLinea=1;
        while(filaValida(fIni)&&columnaValida(cIni)&&(tablero[fIni][cIni]==aBuscar))
        {
            enLinea++;
            cIni++;
            fIni++;
        }
        fIni=f-1;
        cIni=c-1;
        while(filaValida(fIni)&&columnaValida(cIni)&&(tablero[fIni][cIni]==aBuscar))
        {
            enLinea++;
            cIni--;
            fIni--;
        }
       
        if(enLinea>=4)	
            return 1;
       
        //comprobamos que en diagonal hay 4 en linea
        cIni=c+1;
        fIni=f-1;
        enLinea=1;
        while(filaValida(fIni)&&columnaValida(cIni)&&(tablero[fIni][cIni]==aBuscar))
        {
            enLinea++;
            cIni++;
            fIni--;
        }
        fIni=f+1;
        cIni=c-1;
        while(filaValida(fIni)&&columnaValida(cIni)&&(tablero[fIni][cIni]==aBuscar))
        {
            enLinea++;
            cIni--;
            fIni++;
        }
        
        if(enLinea>=4)	
            return 1;
            
        return 0;
    }
    
    public int hayEspacio(int c){
        if (!columnaValida(c))
            return -1;
        else
        {
            int f=0;
            while(filaValida(f)&&(tablero[f][c]==Casilla.LIBRE))
                 f++;
            if(filaValida(f-1))
                return (f-1);
            else
                return -1;
        }
    }

    private boolean filaValida(int f) {
        return 0 <= f && f < numFil;
    }

    private boolean columnaValida(int c) {
        return 0 <= c && c < numCol;
    }
  
    public boolean estaLleno()
    {
    	boolean estaLleno=true;
    	
    	for(int i=0; i<getNumColumnas(); i++)
    	{
    		if(hayEspacio(i)!=-1)
    		{
    			estaLleno=false;
    		}
    	}
    	
    	return estaLleno;
    		
    	
    }
    
    
    public void restaura(int columnas, int filas)
    {
    
    	tablero[filas][columnas]=Casilla.LIBRE;
    }
    
            
}	
