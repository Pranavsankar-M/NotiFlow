# Dev container for Android (Kotlin/Gradle) builds in Codespaces.
# Includes OpenJDK 17, Android SDK, Build-tools, Platform-tools.

FROM ubuntu:22.04

ENV DEBIAN_FRONTEND=noninteractive
ENV ANDROID_SDK_ROOT=/opt/android-sdk
ENV PATH=${PATH}:${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin:${ANDROID_SDK_ROOT}/platform-tools

# ------------------------------------------------------
# Install basic tools
# ------------------------------------------------------
RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    unzip \
    git \
    ca-certificates \
    build-essential \
    zip \
    wget \
    gnupg \
    && rm -rf /var/lib/apt/lists/*

# ------------------------------------------------------
# Install OpenJDK 17
# ------------------------------------------------------
RUN apt-get update && apt-get install -y openjdk-17-jdk-headless && rm -rf /var/lib/apt/lists/*

# ------------------------------------------------------
# Download Android command-line tools
# ------------------------------------------------------
RUN mkdir -p ${ANDROID_SDK_ROOT}/cmdline-tools && mkdir -p /root/.android

WORKDIR /tmp

ARG CMDLINE_ZIP=commandlinetools-linux-11076708_latest.zip

RUN curl -fSL "https://dl.google.com/android/repository/${CMDLINE_ZIP}" -o cmdline.zip \
    && unzip cmdline.zip -d cmdline \
    && mkdir -p ${ANDROID_SDK_ROOT}/cmdline-tools/latest \
    && cp -R cmdline/cmdline-tools/* ${ANDROID_SDK_ROOT}/cmdline-tools/latest/ \
    && rm -rf cmdline cmdline.zip

# ------------------------------------------------------
# Install Android SDK: platform-tools, platform 34, build-tools 34
# ------------------------------------------------------
RUN yes | ${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin/sdkmanager --sdk_root=${ANDROID_SDK_ROOT} \
    "platform-tools" \
    "build-tools;34.0.0" \
    "platforms;android-34" \
    "cmdline-tools;latest"

# Cleanup
RUN rm -rf /var/cache/* /var/lib/apt/lists/*

CMD ["/bin/bash"]
