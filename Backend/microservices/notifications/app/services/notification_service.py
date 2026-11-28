
from app.repositories.notification_repository import NotificationRepository
from app.schemas.notification import NotificationCreate, NotificationUpdate


class NotificationService:

    def __init__(self):
        self.repository = NotificationRepository()

    async def create_notification(self, notification: NotificationCreate):
        data = notification.model_dump()
        return await self.repository.create(data)

    async def get_notifications(self, company_id: str = None):
        return await self.repository.get_all(company_id)

    async def get_notification_by_id(self, notification_id: str):
        return await self.repository.get_by_id(notification_id)

    async def update_notification(
        self,
        notification_id: str,
        notification: NotificationUpdate
    ):
        data = notification.model_dump(exclude_unset=True)

        if not data:
            raise ValueError("Aucune donnée à modifier")

        return await self.repository.update(notification_id, data)

    async def delete_notification(self, notification_id: str):
        return await self.repository.delete(notification_id)
