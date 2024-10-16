# Interface Gráfica com Java

<div align="center">
    <img src="https://github.com/user-attachments/assets/df56a395-8a65-4249-83fb-4f203f264836" alt="image">
</div>

## 🎯 Objetivo
O objetivo desse projeto é desenvolver uma aplicação Java com interface gráfica que explore a criação de interfaces complexas, integração com arquivos, uso de threads e personalização de componentes. A aplicação deve demonstrar a mecânica de ouvintes, manipulação de arquivos, criação de diálogos personalizados e animações dinâmicas.
<br>

## 📝 Descrição do Projeto
O projeto é uma aplicação Java que utiliza uma janela principal (`JFrame`) com os elementos de interface convencionais, como barra de menus (`JMenuBar`), barra de status, título, ícone e outros componentes. A aplicação cumpre os seguintes objetivos:

* Demonstrar a mecânica de ouvintes (implementação interna):  
O projeto implementa corretamente os ouvintes para interações do usuário, incluindo menus, botões e outros componentes.

* Ler arquivos e apresentar o conteúdo na tela conforme escolha do usuário:  
O aplicativo permite a leitura de arquivos de texto e exibir seu conteúdo em uma área da janela principal.

* Mostrar o uso de threads e gráficos dinâmicos por meio de um fundo continuamente animado:  
A janela principal deve exibe um fundo gráfico que se altera dinamicamente, demonstrando o uso de threads em conjunto com desenhos gráficos.

* Usar diálogos padrão para abrir arquivos:  
É utilizado o componente `JFileChooser` para permitir ao usuário selecionar e abrir arquivos no menu “Arquivo”.

* Construir um diálogo de ajuda personalizado com imagens, texto rolável e botões:  
A aplicação possui um diálogo de ajuda acessível pelo menu "Ajuda", incluindo imagens, texto rolável e botões interativos.

## 🖥️ Estrutura da Interface
- Janela Principal (`JFrame`):  
  A janela principal possui barra de título, ícone da aplicação, barra de status e menus.

- Barra de Menus (`JMenuBar`):  
  A barra de menus possui três menus principais: Arquivo, Configuração e Ajuda.

  Menu Arquivo:
  - Abrir Arquivo: Abre um diálogo para selecionar um arquivo de texto e exibir seu conteúdo.
  - Fechar Arquivo: Fecha o arquivo atualmente aberto e limpa a área de texto da tela.
  - Sair: Encerra a aplicação.

  Menu Configuração:
  - Padrões: Permite escolher padrões para o comportamento dinâmico do fundo.
  - Cores: Altera as cores do fundo dinâmico.
  - Velocidade: Ajusta a velocidade das animações do fundo.

  Menu Ajuda:
  - Ajuda: Abre um diálogo personalizado com explicações sobre a aplicação.
  - Sobre: Mostra informações sobre a aplicação (como versão e autores).

