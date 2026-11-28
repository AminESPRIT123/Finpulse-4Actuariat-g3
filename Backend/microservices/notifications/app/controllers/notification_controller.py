
from fastapi import APIRouter, HTTPException, status
from typing import List

from app.services.notification_service import NotificationService
from app.schemas.notification import (
    NotificationCreate,
    NotificationUpdate,
    NotificationResponse
)

router = APIRouter(
    prefix="/api/notifications",
    tags=["Notifications"]
)

service = NotificationService()


def serialize_notification(notification: dict):
    return {
        "id": str(notification["_id"]),
        "companyId": notification["companyId"],
        "alertId": notification.get("alertId"),
        "title": notification["title"],
        "message": notification["message"],
        "severity": notification["severity"],
        "read": notification["read"],
        "createdAt": notification["createdAt"],
        "readAt": notification.get("readAt")
    }


@router.post("/", response_model=NotificationResponse,
             status_code=status.HTTP_201_CREATED)
async def create_notification(notification: NotificationCreate):
    result = await service.create_notification(notification)
    return serialize_notification(result)


@router.get("/", response_model=List[NotificationResponse])
async def get_notifications(companyId: str = None):
    results = await service.get_notifications(companyId)
    return [serialize_notification(item) for item in results]


@router.get("/{notification_id}", response_model=NotificationResponse)
async def get_notification(notification_id: str):
    result = await service.get_notification_by_id(notification_id)

    if not result:
        raise HTTPException(status_code=404, detail="Notification introuvable")

    return serialize_notification(result)


@router.put("/{notification_id}", response_model=NotificationResponse)
async def update_notification(
    notification_id: str,
    notification: NotificationUpdate
):
    try:
        result = await service.update_notification(
            notification_id, notification
        )
    except ValueError as error:
        raise HTTPException(status_code=400, detail=str(error))

    if not result:
        raise HTTPException(status_code=404, detail="Notification introuvable")

    return serialize_notification(result)


@router.delete("/{notification_id}")
async def delete_notification(notification_id: str):
    deleted = await service.delete_notification(notification_id)

    if not deleted:
        raise HTTPException(status_code=404, detail="Notification introuvable")

    return {"message": "Notification supprimée avec succès"}
