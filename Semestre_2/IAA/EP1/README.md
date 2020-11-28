# Curva de Koch

## Explicação do código
public void kochCurve(int px, int py, int qx, int qy, int l)
int delta_x = qx - px;
int delta_y = qy - py;
int ax, ay, bx, by, cx, cy;
double tmp;  

Para desenharmos todos os as retas da curva de Koch, precisaremos fazer algumas verificações. A primeira é se o caso um dos valores de delta_x e delta_y s
são menores que zero (isso pode ocorre pela forma que é passado nos paramêtros do método e se não tratamos a curva não será desenhada completamente).
if (delta_x < 0 || delta_y < 0) 
Depois verificaremos se os valores valores dos deltas são menores que o limiar, para isso mudaremos o sinal do valores dos deltas, pois todo valor negativo é menor que o limiar, mas ao mudarmos, alguns valores acabam sendo maiores e mais retas serão calculadas.
if ((-delta_x < l) && (-delta_y < l))
Caso os deltas sejam menores que o limiar, iremos desenhar a reta PQ usando o método da classe Image drawLine.
super.drawLine(px, py, qx, qy);

No código acaba por ocorre uma duplicação de código, por isso explirei no final o código para calcular os pontos A, B, C.
```Java
// Cálculo do pontos A, B, C e chamada recursiva para as novas retas
```

Caso o valor dos deltas não sejam menores que zero, iremos verificar se são menores que o limiar e caso seja, faremos a mesma etapa já descrita: usar o método drawLine.
```Java
if ((delta_x < l) && (delta_y < l))
super.drawLine(px, py, qx, qy);
```

Para calcular os ponto A (ax e ay) usando uma variável auxiliar que também é usada pelos outros pontos para aumentar a precisão dos valores e o método da classe Math roound para arrendodar os valores. O ponto A se encontra em um terço da reta original.
```Java
tmp = Math.round(px + delta_x / 3);
ax = (int) tmp;
tmp = Math.round(py + delta_y / 3);
ay = (int) tmp;
```
Para calcular o ponto B (bx e by) é um pouco mais complicado, ele se encontra no meio da reta. Ao fazer os cálculos chegamos nas fórmulas a seguir:
```
tmp = Math.round((px + qx) / 2 + Math.sqrt(3) / 6 * (qy - py));
bx = (int) tmp;
tmp = Math.round((py + qy) / 2 + Math.sqrt(3) / 6 * (px - qx));
by = (int) tmp;
```

Para calcular os ponto C (cx e cy) é semelhante ao ponto A, ele se encontra em dois terços da reta original.
```Java
tmp = Math.round(qx - delta_x / 3);
cx = (int) tmp;
tmp = Math.round(qy - delta_y / 3);
cy = (int) tmp;
```

Chamamos o método kochCurve para as novas retas que desenhamos.
```Java
kochCurve(px, py, ax, ay, l);
kochCurve(ax, ay, bx, by, l);
kochCurve(bx, by, cx, cy, l);
kochCurve(cx, cy, qx, qy, l);
```
## Código Completo
## Observações

# Preenchimento de Região
Dado um ponto, uma coordenada representada por `x` e `y`, e toda a região continua em torno dele que possuem a mesma cor. Deve ser pintado aquela região por uma cor definida previamente pelo método 

## Explicação do código
O método `regionFill` é um método público com retorno do tipo `void` que recebe como paramêtro três variáveis do tipo `int`: `x` e `y`, valores da coordnada do pixel, e `reference_rgb`, valor da cor do pixel.
```Java
public void regionFill(int x, int y, int reference_rgb)
```
Nós criamos primeiramente duas vários do tipo `int`, `width` e `height` para representar a largura e a altura da imagem, respectivamente. E com o auxílio de métodos já definidos da classe `Image`, `getWidth` e `getHeight`, passamos o valor requerido respectivamente para cada uma de nossas variáveis.
```Java
int width = super.getWidth();
int height = super.getHeight();
```

Antes de pintar o pixel é necessário verificar se ele está dentro do intervalos de valores válidos (tamanho da imagem), para isso é necessário atender quatro condições:
1. `x` ser maior ou igual a 0;
2. `y` ser maior ou igual a 0;
3. `x` ser menor que `width`;
4. `y` ser menor que `height`.
```Java
if(x >= 0 && y >= 0 && x < width && y < height)
```

Também criamos a variável `color` do tipo int para receber atráves do método `getPixel` da classe `Image`, que recebe como paramêtros as variáveis `x` e `y`, o valor da cor do pixel representado por `x` e `y`.
```Java
int color = super.getPixel(x, y);
```

Também é necessário comparar o valor da variável `color`, que representa a cor do pixel atual, com do paramêtro `reference_rgb`, que representa a cor original do pixel passado pelo arquivo de entrada. E apenas caso as duas variáveis tenham o mesmo valor, será executado as intruções para colorir o pixel e chamar recursivamente o método para os pixels vizinhos (direita, esquerda, cima e baixo).
```Java
if (color == reference_rgb)
```

Podemos usar o método `setColor` da classe `Image` para deixar definido a cor que queremos pintar o pixel, passando por exemplo os três paramêtros para o método: red (255), green(69) e blue(0), obtemos um laranja avermelhado. Mas deixei desabilitada essa opção pelo meu entendimento do exercício, para definir a cor é necessário usar **SET_COLOR**, com a cor que deseja ser usada para pintar, antes de usar **REGION_FILL**.
```Java
// super.setColor(255, 69, 0);
```

E o método `setPixel`, também da classe `Image`, para pintar o pixel representado por `x` e `y` (passados para o paramêtro do método)
```Java
super.setPixel(x, y);
```

Para repetir o processo com os pixels vizinhos (direita, esquerda, cima e baixo), chamamos o método `regionFill` de forma recursiva quatro vezes:
1. Pixel vizinho da direita
- Para pintar o pixel a direita, precisamos validar que `x + 1` não seja maior ou igual ao valor de `width`;
- Passamos como paramêtros `x + 1`, que representa a deslocação de um pixel para direita, e `y`.
```Java
if (x + 1 < width) regionFill(x + 1, y, reference_rgb);
```

2. Pixel vizinho de baixo
- Para pintar o pixel abaixo, precisamos validar que `y + 1` não seja maior ou igual ao valor de `height`;
- Passamos como paramêtros `x` e `y + 1`, que representa a deslocação de um pixel para baixo.
```Java
if (y + 1 < height)regionFill(x, y + 1, reference_rgb);
```
    
3. Pixel vizinho da esquerda
- Para pintar o pixel a esquerda, precisamos validar que x - 1 seja maior ou igual a zero;
- Passamos como paramêtros `x - 1`, que representa a deslocação de um pixel para esquerda, e `y`.
```Java
if (x - 1 >= 0) regionFill(x - 1, y, reference_rgb);
```
    
4. Pixel vizinho de cima
- Para pintar o pixel acima, precisamos validar que `y - 1` seja maior ou igual a zero;
- Passamos como paramêtros `x` e `y - 1`, que representa a deslocação de um pixel para cima.
```Java
if (y - 1 >= 0) regionFill(x, y - 1, reference_rgb);
```    

As quatro chamadas recebem o mesmo valor de `reference_rgb`, que é a cor original do pixel escolhido para ser pintado primeiro.

O código será executado até todos os pixels de determinada região serem preenchidos. Quando encontrar um pixel de uma cor diferente da `reference_rgb` ou estiver fora da da área da imagem, não será mais executado o método e não será chamado para os pixels vizinhos.

## Código Completo
```Java
// Método para o preenchimento de determinada região
public void regionFill(int x, int y, int reference_rgb){
    int width = super.getWidth(); // Valor da largura da imagem
    int height = super.getHeight(); // Valor da altura da imagem

    // Veriricar se a coordenada x e y é válido
    if(x >= 0 && y >= 0 && x < width && y < height){
        int color = super.getPixel(x, y); // Recebe a cor do pixel

        // Verifica se  color e reference_rgb tem o mesmo valor
        if (color == reference_rgb) {
            //super.setColor(255, 69, 0);
            super.setPixel(x, y); // Pinta o pixel da coordenada x e y

            // Chamada recursiva para o pixel a direita
            if (x + 1 < width) regionFill(x + 1, y, reference_rgb);

            // Chamada recursiva para o pixel abaixo
            if (y + 1 < height)regionFill(x, y + 1, reference_rgb); 

            // Chamada recursiva para o pixel a esquerda
            if (x - 1 >= 0) regionFill(x - 1, y, reference_rgb);

            // Chamada recursiva para o pixel acima
            if (y - 1 >= 0) regionFill(x, y - 1, reference_rgb);
        }
    }
}
```

## Observações
- Nenhum erro ou problema foi identificado nessa versão do método com os arquivos de entrada utilizados.
- Uma dúvida sobre o funcionamento do código que é se 
- 

# Como executar

