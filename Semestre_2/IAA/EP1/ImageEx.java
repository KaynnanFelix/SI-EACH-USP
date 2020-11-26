public class ImageEx extends Image {

	public ImageEx(int w, int h, int r, int g, int b){

		super(w, h, r, g, b);
	}

	public ImageEx(int w, int h){

		super(w, h);
	}


	public void kochCurve(int px, int py, int qx, int qy, int l){
		int delta_x = qx - px;
		int delta_y = qy - py;
		int ax, ay, bx, by, cx, cy;
		double tmp; // variavel usada para fazer o calculo em double

		if (delta_x < 0 && delta_y < 0) {
			if ((-delta_x < l) && (-delta_y < l)) {
				super.drawLine(px, py, qx, qy); //  desenhar a reta PQ
			} else {
				// Calculo do Ponto A (Ax e Ay)
        		tmp = Math.round(px + delta_x/3);
        		ax = (int) tmp;
        		tmp = Math.round(py + delta_y/3);
        		ay = (int) tmp;

				// Calculo do Ponto B (Bx e By)
				tmp = Math.round((px + qx)/2 + Math.sqrt(3)/6 * (qy - py));
        		bx = (int) tmp;
        		tmp = Math.round((py + qy)/2 + Math.sqrt(3)/6 * (px - qx));
        		by = (int) tmp;

				// Calculo do Ponto C (Cx e Cy)
        		tmp = Math.round(qx - delta_x/3);			
        		cx = (int) tmp;
        		tmp = Math.round(qy - delta_y/3);
        		cy = (int) tmp;

				kochCurve(px, py, ax, ay, l); // Curva de Koch para a reta PA
				kochCurve(ax, ay, bx, by, l); // Curva de Koch para a reta AB
				kochCurve(bx, by, cx, cy, l); // Curva de Koch para a reta BC
				kochCurve(cx, cy, qx, qy, l); // Curva de Koch para a reta CQ
			}

		} else {
			if ((delta_x < l) && (delta_y < l)) {
				super.drawLine(px, py, qx, qy); //  desenhar a reta PQ
			} else {

				// Calculo do Ponto A (Ax e Ay)
        		tmp = Math.round(px + delta_x/3);
        		ax = (int) tmp;
        		tmp = Math.round(py + delta_y/3);
        		ay = (int) tmp;

				// Calculo do Ponto B (Bx e By)
				tmp = Math.round((px + qx)/2 + Math.sqrt(3)/6 * (qy - py));
        		bx = (int) tmp;
        		tmp = Math.round((py + qy)/2 + Math.sqrt(3)/6 * (px - qx));
        		by = (int) tmp;

				// Calculo do Ponto C (Cx e Cy)
        		tmp = Math.round(qx - delta_x/3);			
        		cx = (int) tmp;
        		tmp = Math.round(qy - delta_y/3);
        		cy = (int) tmp;

				kochCurve(px, py, ax, ay, l); // Curva de Koch para a reta PA
				kochCurve(ax, ay, bx, by, l); // Curva de Koch para a reta AB
				kochCurve(bx, by, cx, cy, l); // Curva de Koch para a reta BC
				kochCurve(cx, cy, qx, qy, l); // Curva de Koch para a reta CQ
		}

		}	
	}

	public void regionFill(int x, int y, int reference_rgb){
		int color = super.getPixel(x, y);
			int width = super.getWidth();
			int height = super.getHeight();
		if(x >= 0 && y >= 0 && x < width && y < height){
			if (color == reference_rgb) {
				super.setColor(255, 69, 0);
				super.setPixel(x, y);
				if (x + 1 < width) regionFill(x + 1, y, reference_rgb);
				if (y + 1 < height)regionFill(x, y + 1, reference_rgb);
				if (x - 1 >= 0) regionFill(x - 1, y, reference_rgb);
				if (y - 1 >= 0) regionFill(x, y - 1, reference_rgb);
			}
		}
	}
}

