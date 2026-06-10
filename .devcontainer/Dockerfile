FROM mcr.microsoft.com/devcontainers/java:1-17-bookworm

# Small tools that are useful inside challenge or test projects.
# as simple as possible, this is not a Kubernetes cluster.
RUN rm -f /etc/apt/sources.list.d/yarn.list \
    && apt-get update \
    && apt-get install -y --no-install-recommends \
        zip \
        unzip \
        wget \
        tree \
        htop \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Install Gradle (binary distribution) so the container has gradle available.
ENV GRADLE_VERSION=8.5
RUN wget -q "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" -O /tmp/gradle.zip \
    && mkdir -p /opt/gradle \
    && unzip -q /tmp/gradle.zip -d /opt/gradle \
    && rm /tmp/gradle.zip \
    && ln -sfn /opt/gradle/gradle-${GRADLE_VERSION}/bin/gradle /usr/local/bin/gradle

WORKDIR /workspace
