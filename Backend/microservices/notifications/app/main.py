
import os
from contextlib import asynccontextmanager

from fastapi import FastAPI
from py_eureka_client import eureka_client

from app.controllers.notification_controller import router


EUREKA_SERVER = os.getenv(
    "EUREKA_SERVER",
    "http://localhost:8761/eureka"
)

SERVICE_HOST = "localhost"
SERVICE_PORT = 8088


@asynccontextmanager
async def lifespan(app: FastAPI):

    await eureka_client.init_async(
        eureka_server=EUREKA_SERVER,
        app_name="NOTIFICATIONS",
        instance_host=SERVICE_HOST,
        instance_port=SERVICE_PORT,
        renewal_interval_in_secs=30
    )

    print("NOTIFICATIONS enregistré dans Eureka")

    yield

    await eureka_client.stop_async()
    print("NOTIFICATIONS désenregistré de Eureka")


app = FastAPI(
    title="FINPULSE - Notifications Service",
    description="Microservice de gestion des notifications",
    version="1.0.0",
    lifespan=lifespan
)

app.include_router(router)


@app.get("/")
async def root():
    return {
        "service": "NOTIFICATIONS",
        "message": "Microservice Notifications opérationnel",
        "status": "UP"
    }


@app.get("/health")
async def health_check():
    return {
        "service": "NOTIFICATIONS",
        "status": "UP"
    }
