$ErrorActionPreference = "Stop"
Set-Location $PSScriptRoot
$VenvPython = Join-Path $PSScriptRoot ".venv\Scripts\python.exe"
if (-not (Test-Path $VenvPython)) {
    throw "Python environment not found. Run .\setup.ps1 first."
}

$env:PLAYWRIGHT_BROWSERS_PATH = Join-Path $PSScriptRoot ".playwright-browsers"
if (-not $env:PLAYWRIGHT_HEADLESS) { $env:PLAYWRIGHT_HEADLESS = "true" }
& $VenvPython start.py
