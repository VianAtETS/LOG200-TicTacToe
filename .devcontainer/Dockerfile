# JDK 21 (LTS) complet : java, javac et jdb (débogueur en ligne de commande)
FROM mcr.microsoft.com/devcontainers/java:21-bookworm

# Version épinglée de Typst (https://github.com/typst/typst/releases)
ARG TYPST_VERSION=0.15.1

# Polices (accents français) et utilitaires pour Typst et le terminal
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get install -y --no-install-recommends \
        ca-certificates \
        curl \
        xz-utils \
        fonts-linuxlibertine \
        fonts-noto-core \
        fonts-dejavu \
        tree \
        less \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# Typst CLI (binaire statique musl), selon l'architecture
RUN case "$(dpkg --print-architecture)" in \
        amd64) TYPST_ARCH=x86_64 ;; \
        arm64) TYPST_ARCH=aarch64 ;; \
        *) echo "Architecture non supportée" >&2; exit 1 ;; \
    esac \
    && curl -fsSL "https://github.com/typst/typst/releases/download/v${TYPST_VERSION}/typst-${TYPST_ARCH}-unknown-linux-musl.tar.xz" \
        | tar -xJ -C /tmp \
    && install -m 0755 "/tmp/typst-${TYPST_ARCH}-unknown-linux-musl/typst" /usr/local/bin/typst \
    && rm -rf /tmp/typst-*

# JUnit (lanceur autonome) pour les tests unitaires dans test/, sans outil de build
ARG JUNIT_VERSION=6.1.3
RUN mkdir -p /opt/junit \
    && curl -fsSL -o "/opt/junit/junit-platform-console-standalone-${JUNIT_VERSION}.jar" \
        "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/${JUNIT_VERSION}/junit-platform-console-standalone-${JUNIT_VERSION}.jar"
