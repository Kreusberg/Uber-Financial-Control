# Uber Financial Control 🚗

**Uber Financial Control** é um aplicativo Android desenvolvido em **Kotlin** para auxiliar no gerenciamento e controle financeiro de gastos com serviços de transporte por aplicativo (Uber).

O principal objetivo é ter um controle automatizado, onde um registro num banco de dados NoSql ([firestore](https://firebase.google.com/docs/firestore?hl=pt-br)) é gravado assim que uma notificação do aplicativo é disparada ao confirmar o pagamento da viagem, facilitando o controle financeiro.

---

## 🛠️ Tecnologias e Ferramentas

- **Linguagem:** [Kotlin](https://kotlinlang.org/)
- **Build System:** [Gradle (Kotlin DSL)](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- **IDE:** Android Studio

## 📁 Estrutura do Repositório

O projeto segue a estrutura padrão do ecossistema Android:

* `/app`: Contém o código-fonte (UI e Lógica), recursos (layouts, drawables) e o manifesto do Android.
* `/gradle`: Arquivos de configuração do Wrapper do Gradle.
* `build.gradle.kts`: Configurações de dependências e build de nível de projeto.

## 🔧 Como Rodar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/Kreusberg/Uber-Financial-Control.git](https://github.com/Kreusberg/Uber-Financial-Control.git)
    ```
2.  **Abra no Android Studio:**
    Vá em `File > Open` e selecione a pasta raiz do projeto.
3.  **Sincronize o Gradle:**
    Aguarde o download das dependências (conforme definido no `settings.gradle.kts`).
4.  **Execute:**
    Selecione um Emulador ou Dispositivo Físico e clique no botão **Run** (`Shift + F10`).

## 👤 Autor

**Lucas Kreusberg**
