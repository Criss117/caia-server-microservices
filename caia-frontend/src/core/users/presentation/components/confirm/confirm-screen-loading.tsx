import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/core/shared/components/ui/card";

const ConfirmScreenLoading = () => {
  return (
    <div className="h-screen flex items-center justify-center">
      <Card className="bg-lightprimary-300 w-[50%] text-center">
        <CardHeader>
          <CardTitle className="text-2xl">Verificando token</CardTitle>
          <CardDescription></CardDescription>
        </CardHeader>
        <CardContent>
          <p>Espere mientras confirmamos tu cuenta</p>
        </CardContent>
      </Card>
    </div>
  );
};

export default ConfirmScreenLoading;
