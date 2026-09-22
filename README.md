# LOG200-TicTacToe

Welcome to the **LOG200-TicTacToe** repository! This repository was generated from a template to get you started quickly.

## 🚀 Getting Started

To get started with this project:

1. Clone the repository:
   ```bash
   git clone https://github.com/VianAtETS/LOG200-TicTacToe.git
   cd LOG200-TicTacToe
   ```
2. Open the folder in VS Code and choose **Reopen in Container**. The dev
   container provides JDK 21 (with `jdb`), the VS Code Java debugger, Typst and
   Tinymist.
3. Compile the lab (or run the default build task, `Ctrl+Shift+B`):
   ```bash
   javac -d bin src/*.java
   ```
4. Debug with `F5` using the **Déboguer Test (TicTacToe)** configuration
   (requires a `src/Test.java` with a `main` method).
5. Write unit tests as `test/*Test.java` (JUnit 6, default package). Run them
   with:
   ```bash
   javac -d bin src/*.java
   javac -d bin-test -cp bin:/opt/junit/* test/*.java
   java -jar /opt/junit/*.jar execute -cp bin:bin-test --scan-class-path
   ```
6. Write the submission report in `remise/rapport.typ` (based on the
   `remise/ets-report.typ` template) and preview it live with Tinymist
   (**Typst: Show Preview**), or from the terminal (the `--root .` flag lets the
   report include files from `src/`):
   ```bash
   typst watch --root . remise/rapport.typ
   ```

## 🔄 CI/CD

- **CI** (`.github/workflows/ci.yml`): on every pull request and push, checks
  that the Java sources compile, runs the JUnit tests in `test/` (skipped if
  there are none) and compiles the Typst report.
- **Release** (`.github/workflows/release.yml`): on every push to `main`, runs
  the CI, then computes the next semantic version from the
  [Conventional Commits](https://www.conventionalcommits.org/) since the last
  tag and publishes a GitHub release with a zip of `src/*.java` and
  `rapport.pdf`:
  - `feat!:` or `BREAKING CHANGE:` → major, `feat:` → minor,
    `fix:`/`perf:` → patch;
  - other types (`docs`, `ci`, `chore`, …) do not create a release.

  With squash merges, the **PR title** becomes the commit message, so it must
  follow the convention.

## 📁 Project Structure

The repository contains the following directories and files:

- `.devcontainer/` - Development container configuration for VS Code
  - `devcontainer.json` - Dev container settings
  - `Dockerfile` - Container image definition
- `.github/` - GitHub-specific configurations
  - `ISSUE_TEMPLATE/` - Issue templates (bug reports, feature requests)
  - `pull_request_template.md` - Pull request template
  - `workflows/` - GitHub Actions workflow files
- `.vscode/` - VS Code workspace settings, tasks and debug configurations
- `src/` - Java sources for the lab (`Board`, `CPUPlayer`, `Mark`, `Move`)
- `test/` - JUnit unit tests
- `remise/` - Typst report (`rapport.typ`), ETS template and logo
- `tictactoe.pdf` - Lab statement
- `.dockerignore` - Docker build exclusions
- `.gitattributes` - Git attributes configuration
- `.gitignore` - Git ignore patterns
- `README.md` - This file

## 🛠 Features

- Initialized from a reusable template for quick setup.
- Pre-configured workflows for automation and CI/CD.
- Placeholder sections for documentation, testing, and development.

## 📖 Documentation

Check the project files and comments for guidance. You can expand this section as your project grows.

## 🤝 Contributing

Contributions are welcome! Feel free to open issues, submit pull requests, or suggest improvements.

## 📝 License

Specify your license here (if any). For example: MIT, Apache 2.0, etc.

Happy coding! 🎉
