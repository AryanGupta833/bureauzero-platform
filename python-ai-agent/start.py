import sys
import asyncio

# Force Windows Selector Event Loop Policy for Playwright compatibility
if sys.platform == "win32":
    asyncio.set_event_loop_policy(asyncio.WindowsSelectorEventLoopPolicy())

import uvicorn

if __name__ == "__main__":
    uvicorn.run("ai_worker:app", host="127.0.0.1", port=8001, reload=False)
