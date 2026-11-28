
from fastapi import FastAPI

app = FastAPI(
    title="FINPULSE - Notifications Service",
    description="Microservice de gestion des notifications",
    version="1.0.0"
)

@app.get("/")
def root():
    return {
        "service": "NOTIFICATIONS",
        "message": "Microservice Notifications opérationnel",
        "status": "UP"
    }

@app.get("/health")
def health_check():
    return {
        "service": "NOTIFICATIONS",
        "status": "UP"
    }
