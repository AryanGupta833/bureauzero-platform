#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")"
export PLAYWRIGHT_BROWSERS_PATH="$PWD/.playwright-browsers"
export PLAYWRIGHT_HEADLESS="${PLAYWRIGHT_HEADLESS:-true}"
if [[ ! -x .venv/bin/python ]]; then
  echo "Python environment not found. Run ./setup.sh first." >&2
  exit 1
fi
exec .venv/bin/python start.py
