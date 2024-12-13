import { useState } from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { useSession } from "next-auth/react";
import { useRouter } from "next/navigation";

import { SendPaperDto } from "../models/types";
import { SendPaperSchema } from "../models/schemas";
import { httpClient } from "@/core/shared/lib/config/http.config";
import { ROUTES } from "@/core/shared/lib/constants/routes";

interface Props {
  slug: string;
  keys: string[];
}

const defaultValues: SendPaperDto = {
  title: "titulo 1",
  description: "descripcion 1",
  keys: "hola, mundo",
  conferenceSlug: "",
};

const useSendPaper = ({ slug, keys }: Props) => {
  const { data: session } = useSession();
  const router = useRouter();
  const [file, setFile] = useState<FileList | null>(null);
  const form = useForm<SendPaperDto>({
    resolver: zodResolver(SendPaperSchema),
    defaultValues: {
      ...defaultValues,
      conferenceSlug: slug,
    },
  });

  const onSubmit = form.handleSubmit(async (sendPaperDto) => {
    const formData = new FormData();

    console.log({ ...sendPaperDto, keys: keys.join(","), file });

    formData.append("title", sendPaperDto.title);
    formData.append("description", sendPaperDto.description);
    formData.append("keys", keys.join(","));
    formData.append("conferenceSlug", sendPaperDto.conferenceSlug);

    if (!file) {
      form.setError("root", { message: "Se requiere un archivo" });
      return;
    }

    formData.append("file", file[0]);

    const { status } = await httpClient.post("/papers/new", formData, {
      headers: {
        Authorization: `Bearer ${session?.jwt}`,
        "Content-Type": "multipart/form-data",
      },
    });

    if (status !== 200) {
      form.setError("root", { message: "Error al subir el paper" });
      return;
    }

    router.push(ROUTES.DASHBOARD.PAPERS);
  });

  return { form, setFile, onSubmit };
};

export default useSendPaper;
