# BureauZero Platform

BureauZero contains a Spring Boot dashboard and a Python FastAPI/Playwright worker.

## Prerequisites

- Java 21
- Maven 3.8+
- Python 3.10+
- Internet access for the initial Python package and Playwright browser installation

## Configure secrets

Copy `.env.example` to `.env` in the repository root and set `GEMINI_API_KEY` to your own key. The Python worker loads this file automatically. `.env` is ignored by Git; commit only `.env.example`.

Live myScheme discovery does not require a Gemini key. Document extraction and legacy DOB correction do.

## Setup the Python worker

**Windows PowerShell** (from the repository root):

```powershell
Copy-Item .env.example .env
notepad .env
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
./python-ai-agent/setup.ps1
```

**macOS / Linux** (from the repository root):

```sh
cp .env.example .env
# Edit .env and add your key if needed.
./python-ai-agent/setup.sh
```

## Run the application

Start the worker in one terminal:

```powershell
# Windows PowerShell
./python-ai-agent/run.ps1
```

```sh
# macOS / Linux
./python-ai-agent/run.sh
```

Start the Spring Boot dashboard in another terminal, from `spring-boot-core`:

```sh
mvn spring-boot:run
```

Open <http://localhost:8080>. The FastAPI worker listens on <http://127.0.0.1:8001>.

Set `PLAYWRIGHT_HEADLESS=false` in `.env` to show the browser during automation.
