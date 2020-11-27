Nós criamos primeiramente duas vários do tipo int, width e height para representar a largura e a altura da imagem, respectivamente. E com o auxílio de métodos já definidos da classe Imagem, getWidth e getHeight, passamos o valor requerido respectivamente para cada uma de nossas variáveis.
```Java
int width = super.getWidth();
int height = super.getHeight();
```
Antes de pintar o pixel é necessário verificar se ele está dentro do intervalos de valores válidos (tamanho da imagem), para isso é necessário atender quatro condições:
1. x ser maior ou igual a 0;
2. y ser maior ou igual a 0;
3. x ser menor que width;
4. y ser menor que height.
```Java
if(x >= 0 && y >= 0 && x < width && y < height)
```


Também criamos a variável color do tipo int para receber atráves do método getPixel da classe Image, que recebe como paramêtros as variáveis x e y, o valor da cor do pixel representado por x e y.
```Java
int color = super.getPixel(x, y);
```

Também é necessário comparar o valor da variável color, que representa a cor do pixel atual, com do paramêtro reference_rgb, que representa a cor original do pixel passado pelo arquivo de entrada. E apenas caso as duas variáveis tenham o mesmo valor, será executado as intruções para colorir o pixel e chamar recursivamente o método para os pixels vizinhos (direita, esquerda, cima e baixo).
```Java
if (color == reference_rgb)
```

Usamos setColor da classe Image para definir a cor que queremos pintar o pixel, passando os três paramêtros para o método: red (255), green(69) e blue(0), obtemos um laranja avermelhado.
```Java
super.setColor(255, 69, 0);
```

E o método setPixel, também da classe Image, para pintar o pixel representado por x e y (passados para o paramêtro do método)
```Java
super.setPixel(x, y);
```

Para repetir o processo com os pixels vizinhos (direita, esquerda, cima e baixo), chamamos o método regionFill de forma recursiva quatro vezes:
1. Pixel vizinho da direita
- Para pintar o pixel a direita, precisamos validar que x + 1 não seja maior ou igual ao valor de width;
- Passamos como paramêtros x + 1, que representa a deslocação de um pixel para direita, e y.
```Java
if (x + 1 < width) regionFill(x + 1, y, reference_rgb);
```

2. Pixel vizinho de baixo
- Para pintar o pixel abaixo, precisamos validar que y + 1 não seja maior ou igual ao valor de height;
- Passamos como paramêtros x e y + 1, que representa a deslocação de um pixel para baixo.
```Java
if (y + 1 < height)regionFill(x, y + 1, reference_rgb);
```
    
3. Pixel vizinho da esquerda
- Para pintar o pixel a esquerda, precisamos validar que x - 1 seja maior ou igual a zero;
- Passamos como paramêtros x - 1, que representa a deslocação de um pixel para esquerda, e y.
```Java
if (x - 1 >= 0) regionFill(x - 1, y, reference_rgb);
```
    
4. Pixel vizinho de cima
- Para pintar o pixel acima, precisamos validar que y - 1 seja maior ou igual a zero;
- Passamos como paramêtros x e y, que representa a deslocação de um pixel para cima.
```Java
if (y - 1 >= 0) regionFill(x, y - 1, reference_rgb);
```    

As quatro chamadas recebem o mesmo valor de reference_rgb, que é a cor original do pixel escolhido para ser pintado primeiro.

## Observações
- Nenhum erro ou problema foi identificado nessa versão do método com os arquivos de entrada utilizados.
- 
